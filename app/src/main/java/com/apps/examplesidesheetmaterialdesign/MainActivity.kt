package com.apps.examplesidesheetmaterialdesign

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.apps.examplesidesheetmaterialdesign.screens.MySideSheetComposeDialogContent
import com.bruno.daniel.navarro.nunez.sidesheetdialogcompose.component.SheetActions
import com.bruno.daniel.navarro.nunez.sidesheetdialogcompose.component.SheetAlignsParams
import com.bruno.daniel.navarro.nunez.sidesheetdialogcompose.component.SideSheet
import com.google.android.material.sidesheet.SideSheetDialog

class MainActivity : AppCompatActivity() {

    private lateinit var btnShowSideSheetDialog: Button
    private lateinit var sheet: ComposeView

    var isVisibleDrawer by mutableStateOf(false)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnShowSideSheetDialog = findViewById(R.id.btnOpenSideSheet)
        sheet = findViewById(R.id.sideSheet)
        val btnOpenSideSheetComposeDialog: Button = findViewById(R.id.btnOpenSideSheetComposeDialog)


        sheet.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                SideSheet(
                    key = isVisibleDrawer,
                    sheetAction = SheetActions(
                        onClose = {
                            closeOrShowDrawer(it)
                        },
                        callBackState = {
                            updateVisibleDrawer(it)
                        }
                    ),
                    sheetAlignsParams = SheetAlignsParams(
                        sheetAlignment = Alignment.BottomStart,
                        closeAlignment = Alignment.End
                    )
                ) {
                    MySideSheetComposeDialogContent()
                }


            }
        }



        btnShowSideSheetDialog.setOnClickListener {
            val sideSheetDialog = SideSheetDialog(this)
            sideSheetDialog.setContentView(R.layout.side_sheet_content_layout)
            sideSheetDialog.setSheetEdge(Gravity.END)
            sideSheetDialog.setCanceledOnTouchOutside(true)
            sideSheetDialog.setOnDismissListener {
                Toast.makeText(this, "Dismissed", Toast.LENGTH_SHORT).show()
            }
            sideSheetDialog.isDismissWithSheetAnimationEnabled = true

            if (sideSheetDialog.isShowing){
                sideSheetDialog.hide()
            }else {
                sideSheetDialog.show()
            }

        }


        btnOpenSideSheetComposeDialog.setOnClickListener {
            closeOrShowDrawer(true)
        }

    }

    private fun closeOrShowDrawer(show: Boolean){
        isVisibleDrawer = show
        sheet.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun updateVisibleDrawer(show: Boolean){
        isVisibleDrawer = show
        sheet.visibility = if (show) View.VISIBLE else View.GONE
    }
}