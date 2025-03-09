package com.apps.examplesidesheetmaterialdesign.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MySideSheetComposeDialogContent(){
    Column {
        Text(text = "Este es mi primer SideSheetComposeDialog!!")

        Button(onClick = { /*TODO*/ }) {
            Text(text = "Click Aqui!!")
        }
    }
}


@Composable
@Preview(showBackground = true, showSystemUi = true, device = Devices.NEXUS_5)
fun Preview(){
    MySideSheetComposeDialogContent()
}