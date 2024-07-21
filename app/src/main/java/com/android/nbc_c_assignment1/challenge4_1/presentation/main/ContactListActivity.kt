package com.android.nbc_c_assignment1.challenge4_1.presentation.main

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
import com.android.nbc_c_assignment1.challenge4_1.data.database.DataSource
import com.android.nbc_c_assignment1.challenge4_1.data.entity.ContactListDataEntity
import com.android.nbc_login.R
import com.android.nbc_login.databinding.ActivityContactListBinding

class ContactListActivity : AppCompatActivity() {
    private val binding by lazy { ActivityContactListBinding.inflate(layoutInflater) }
    private val viewModel: ContactListViewModel by viewModels<ContactListViewModel> {
        ContactListViewmodelFactory()
    }
    private lateinit var adapter: ContactListAdapter
    private val permissionList = arrayOf(Manifest.permission.READ_CONTACTS, Manifest.permission.CALL_PHONE,)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        DataSource.initDataSource(this, contentResolver)

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

        if (ActivityCompat.checkSelfPermission(this, permissionList[0]) != PackageManager.PERMISSION_GRANTED) {
            requestPermissionLauncher.launch(permissionList)
        } else {
            viewModel.setContactListData()
        }

        adapter = ContactListAdapter(viewModel)

        adapter.submitList(viewModel.getList())

        viewModel.contactListData.observe(this) {
            println("옵저버 확인")
            adapter.submitList(viewModel.getList())
        }

        // 함수를 사용하지 않은 SAM 변환
//        adapter.itemLongClick = ContactListAdapter.ItemClick {
//            if(ActivityCompat.checkSelfPermission(this@ContactListActivity, permissionList[1]) == PackageManager.PERMISSION_GRANTED) {
//                val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:01084381217"))
//                startActivity(intent)
//            } else {
//                requestPermissionLauncher.launch(permissionList)
//            }
//        }

        // 함수를 사용한 SAM 변환
        adapter.setOnItemLongClickListener {
            if(ActivityCompat.checkSelfPermission(this@ContactListActivity, permissionList[1]) == PackageManager.PERMISSION_GRANTED) {
                val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:01084381217"))
                startActivity(intent)
            } else {
                requestPermissionLauncher.launch(permissionList)
            }
        }

        binding.recyclerContactList.adapter = adapter
        binding.recyclerContactList.layoutManager = LinearLayoutManager(this)
    }

}