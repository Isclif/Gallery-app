package com.example.galleryapp

// Import nécessaire pour utiliser les composables et les fonctions utiles de Jetpack Compose
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.galleryapp.ui.theme.GalleryAppTheme

// Classe principale de l'activité où l'application démarre
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GalleryAppTheme {
                // Surface contient tout le contenu de l'application
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    // Appelle notre fonction GalleryScreen qui gère l'affichage de l'image et des boutons
                    GalleryScreen()
                }
            }
        }
    }
}

// Composable principal qui affiche une image avec des boutons pour la navigation
@Composable
fun GalleryScreen() {
    // Liste des images que tu veux afficher, ici on utilise des images stockées dans res/drawable
    val images = listOf(
        R.drawable.image1,
        R.drawable.image2,
        R.drawable.image3
    )

    var description: String

    // Variable d'état pour garder la trace de l'image affichée (index courant)
    var currentIndex by remember { mutableStateOf(0) }

    // Column organise verticalement les éléments (image, texte et boutons ici)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        // verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.padding(15.dp)
        ) {
            Text(text = "Gallery App", style = MaterialTheme.typography.h6)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()  // Taille du composable
                .height(400.dp)
                .background(color = Color.White)  // Couleur de fond avec coins arrondis
                .shadow(
                    elevation = 5.dp,  // Taille de l'ombre
                    shape = RoundedCornerShape(5.dp) // Forme de l'ombre (ici arrondie)
                )

        ) {
            // Affichage de l'image actuelle
            Image(
                painter = painterResource(id = images[currentIndex]),
                contentDescription = "Image $currentIndex",
                modifier = Modifier
                    .size(500.dp)
                    .padding(16.dp)
            )
        }

        if(currentIndex === 0){
            description = "Still Life of Blue Rose and Other Flowers"
        } else if(currentIndex === 1){
            description = "paysage 2"
        }else{
            description = "paysage 3"
        }

        Row(
            modifier = Modifier.padding(30.dp)
        ) {}

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorResource(id = R.color.light_gray_blue),
                    shape = RoundedCornerShape(8.dp)
                )  // Couleur de fond avec coins arrondis
                .padding(10.dp)
        ) {
            Column { // Utilisation de Column pour empiler les textes
                Text(
                    text = "Image $description",
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.albert_sans_light)), // Utilisation de la police personnalisée
                        fontSize = 30.sp // Taille de police
                    )
                )

                Row() {
                    Text(
                        text = "Owen Scott",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold, // Met le texte en gras
                            fontSize = 20.sp // Taille de la police
                        )
                    )

                    Text(
                        text = "(2021)",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.albert_sans_light)), // Utilisation de la police personnalisée
                            fontSize = 15.sp // Taille de police
                        ),
                        modifier = Modifier.padding(6.dp)
                    )
                }

            }
        }

        Row(
            modifier = Modifier.padding(20.dp)
        ) {}

        // Row pour organiser les boutons horizontalement
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Bouton Previous pour afficher l'image précédente
            Button(
                onClick = {
                    // Si l'index est supérieur à 0, on décrémente l'index
                    if (currentIndex > 0) {
                        currentIndex--
                    }
                },
                modifier = Modifier.width(150.dp),
                shape = RoundedCornerShape(15.dp),
                enabled = currentIndex > 0 // Désactiver si c'est la première image
            ) {
                Text("Previous")
            }

            // Bouton Next pour afficher l'image suivante
            Button(
                onClick = {
                    // Si on n'est pas à la dernière image, on incrémente l'index
                    if (currentIndex < images.size - 1) {
                        currentIndex++
                    }
                },
                modifier = Modifier.width(150.dp),
                shape = RoundedCornerShape(12.dp),
                enabled = currentIndex < images.size - 1 // Désactiver si c'est la dernière image
            ) {
                Text("Next")
            }
        }
    }
}


// Fonction pour la prévisualisation dans Android Studio
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GalleryAppTheme {
        // Appelle GalleryScreen dans la preview pour voir à quoi ça ressemble
        GalleryScreen()
    }
}
