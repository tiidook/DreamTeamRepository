package me.codezfox.extension

import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.support.v4.app.Fragment

fun Fragment.camera(uri: Uri, requestCode: Int) {
    val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri)

    this.startActivityForResult(takePictureIntent, requestCode)
}

fun Fragment.gallery(requestCode: Int) {
    val intent = Intent(Intent.ACTION_GET_CONTENT)

    intent.type = "image/*"

    this.startActivityForResult(intent, requestCode)
}