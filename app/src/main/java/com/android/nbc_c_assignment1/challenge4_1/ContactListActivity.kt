package com.android.nbc_c_assignment1.challenge4_1

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.nbc_login.R
import com.android.nbc_login.databinding.ActivityContactListBinding

class ContactListActivity : AppCompatActivity() {
    private val binding by lazy { ActivityContactListBinding.inflate(layoutInflater) }
    private val viewModel: ContactListViewModel by viewModels()
    private lateinit var adapter: ContactListAdapter
    private val permissionList = arrayOf(Manifest.permission.READ_CONTACTS, Manifest.permission.CALL_PHONE,)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            permissions.entries.forEach{ permission ->
                when {
                    permission.value -> {
                        println("permission granted ${permission.key}")
                    }
                    shouldShowRequestPermissionRationale(permission.key) -> {
                        println("permission required ${permission.key}")
                    }
                    else -> println("permission denied ${permission.key}")
                }
            }
        }

        requestPermissionLauncher.launch(permissionList)

        val baseList = listOf(
            ContactListData(R.drawable.profile_basic, "홍길동", "010-0000-0000", false),
            ContactListData(R.drawable.profile_basic, "김길동", "010-1234-0000", false),
            ContactListData(R.drawable.profile_basic, "박길동", "010-0567-0000", true),
            ContactListData(R.drawable.profile_basic, "박석두", "010-8790-0000", false),
        )

        if (ActivityCompat.checkSelfPermission(this, permissionList[0]) == PackageManager.PERMISSION_GRANTED) {
            viewModel.submitList(
                loadContactData() ?: baseList
            )
        }
        else {
            viewModel.submitList(
                baseList
            )
            requestPermissionLauncher.launch(permissionList)
        }

        println("${viewModel.getList()}")

        adapter = ContactListAdapter(viewModel)

        binding.recyclerContactList.adapter = adapter
        binding.recyclerContactList.layoutManager = LinearLayoutManager(this)

        viewModel.contactListData.observe(this) {
            println("옵저버 확인")
            adapter.notifyDataSetChanged()
        }

        adapter.itemLongClick = object : ContactListAdapter.ItemClick {
            override fun onClick() {
                if(ActivityCompat.checkSelfPermission(this@ContactListActivity, permissionList[1]) == PackageManager.PERMISSION_GRANTED) {
                    val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:01038868947"))
                    startActivity(intent)
                } else {
                    requestPermissionLauncher.launch(permissionList)
                }
            }
        }
    }

    private fun loadContactData(): List<ContactListData>? {
        val cursor = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            arrayOf(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER), null, null, null
        )
        val list: MutableList<ContactListData> = mutableListOf()
        if(cursor != null) {
            while (cursor.moveToNext()) {
                list.add(ContactListData(R.drawable.profile_basic, cursor.getString(0), cursor.getString(1), false))
            }
        }
        return list
    }

}