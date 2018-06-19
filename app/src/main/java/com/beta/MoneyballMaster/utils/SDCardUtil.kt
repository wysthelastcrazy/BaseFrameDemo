package com.beta.MoneyballMaster.utils

import android.os.Environment

import com.beta.MoneyballMaster.common.Global

import java.io.File

object SDCardUtil {
    private val TAG = "SDCardUtil"

    private val FileName = "/menkoudai_2b/pic"
    private val FileNameCamera = "/menkoudai_2b/camera"
    private val FileNameSave = "/menkoudai_2b/save"

    val isSDCardExist: Boolean
        get() {
            var isExits = false
            isExits = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
            isExits = true
            return isExits
        }

    val isSDCardExistReal: Boolean
        get() {
            var isExits = false
            isExits = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
            MyLog.debug("dd", "[initLayout] isExits:$isExits")
            return isExits
        }

    /***
     * 得到SD卡路径
     * @return
     */
    private val sdCardPath: String?
        get() {
            var path: String? = null
            if (isSDCardExistReal) {
                path = Environment.getExternalStorageDirectory().toString()
            } else {
                path = Global.getContext().filesDir.absolutePath
            }
            return path
        }


    val sdCardPath_Pic: String
        get() {
            val path = sdCardPath + FileName
            return path
        }

    /***
     * 获取carmerpath路径
     * @return
     */
    val sdCardCamerPath: String
        get() {
            val path = sdCardPath + FileNameCamera
            return path
        }

    /***
     * 获取文件保存路径
     * @return
     */
    val savePath: String
        get() {
            val path = sdCardPath + FileNameSave
            return path
        }

    /***
     * 判断SD卡是否可用
     * @return
     */
    val isSDCardEnable: Boolean
        get() {
            val file = File(sdCardCamerPath)
            return if (file.exists()) {
                true
            } else false
        }

    init {
        //创建pic目录
        var filePath = sdCardPath_Pic
        var file = File(filePath)
        if (file != null && !file.exists()) {
            file.mkdirs()
        }
        //创建cammer目录
        filePath = sdCardCamerPath
        file = File(filePath)
        if (file != null && !file.exists()) {
            file.mkdirs()
        }
        //创建save目录
        filePath = savePath
        file = File(filePath)
        if (file != null && !file.exists()) {
            file.mkdirs()
        }
    }
}
