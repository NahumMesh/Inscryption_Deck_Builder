package com.example.inscryptiondeckbuilder.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.storage.FirebaseStorage

@Composable
fun rememberFireBaseStorageImage(
    imageUrl: String?,
    contentDescription: String?
) : Painter {
    return rememberAsyncImagePainter(
        model = imageUrl
    )
}

@Composable
fun FirebaseImageItem(imageUrl: String) {
    val painter: Painter = rememberFireBaseStorageImage(
        imageUrl = imageUrl,
        contentDescription = "Image"
    )

    Image(
        painter = painter,
        contentDescription = "Image",
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    )
}

@Composable
fun FirebaseImageGrid(imageUrls: List<String>) {
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(imageUrls) { imageUrl ->
            FirebaseImageItem(imageUrl = imageUrl) }
    }
}

@Composable
fun CardCatalog(imageUrls: List<String>) {
    FirebaseImageGrid(imageUrls = imageUrls)
}

@Composable
fun CardCatalogScreen(cardCatalogViewModel: CardCatalogViewModel) {
    val imageUrls by cardCatalogViewModel.imageUrls.observeAsState(emptyList())

    DisposableEffect(cardCatalogViewModel) {
        cardCatalogViewModel.fetchImageUrls()
        onDispose {  }
    }

    CardCatalog(imageUrls = imageUrls)
}

class CardCatalogViewModel : ViewModel() {
    private val storage = FirebaseStorage.getInstance()
    private val storageRef = storage.reference.child("card_scans")

    private val _imageUrls = MutableLiveData<List<String>>()
    val imageUrls: LiveData<List<String>> get() = _imageUrls

    fun fetchImageUrls() {
        storageRef.listAll()
            .addOnSuccessListener { listResult ->
                val urls = listResult.items.map { item ->
                    item.downloadUrl.toString()
                }

                _imageUrls.value = urls
            }
    }
}

