package it.datalux.homeworktest.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import it.datalux.homeworktest.domain.entity.Photo
import it.datalux.homeworktest.presentation.screen.photoDetails.PhotoDetailsScreen
import it.datalux.homeworktest.presentation.screen.photosList.PhotoListScreen

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = PhotosList
    ) {
        composable<PhotosList> {
            PhotoListScreen(
                onItemClick = { navController.navigate(route = it) },
            )
        }

        composable<Photo> { backStackEntry ->
            val photoDetail: Photo = backStackEntry.toRoute()
            PhotoDetailsScreen(
                photo = photoDetail,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}