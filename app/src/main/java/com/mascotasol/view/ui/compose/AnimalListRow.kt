package com.mascotasol.view.ui.compose

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mascotasol.R
import com.mascotasol.data.model.Animal


@Composable
fun AnimalListItem(animal: Animal, navigateToAnimalDetail: (Animal) -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        backgroundColor = Color.DarkGray,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Row(modifier = Modifier.clickable(onClick = {
            navigateToAnimalDetail.invoke(animal)
        })) {
            AnimalImage(animal)
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = animal.name, style = typography.h6)
                Text(text = animal.sex, style = typography.caption)
            }
        }
    }
}

@Composable
private fun AnimalImage(animal: Animal) {
    Image(
        painter = painterResource(id = R.drawable.animaldog),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .size(84.dp)
            .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
    )
}

@Preview
@Composable
fun PreviewAnimalItem() {
    AnimalListItem(
        animal = Animal(
            1,
            "Hola",
            "raza",
            "sexo",
            2,
            1,
            "Desc"
        ),
        navigateToAnimalDetail = { }
    )
}