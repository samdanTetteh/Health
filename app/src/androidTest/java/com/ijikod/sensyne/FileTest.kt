package com.ijikod.sensyne

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FileTest {

    @Test
    fun testFileCreation(){
        val file  = FileHelper.createFile(ApplicationProvider.getApplicationContext(), "Hospital", "csv")
        Assert.assertNotNull(file)
    }
}