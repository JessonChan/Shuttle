plugins {
    id("shuttle.android")
}

shuttleAndroid {
    useCompose()
    androidApp(
        id = "studio.forface.shuttle",
        versionCode = 1,
        versionName = "0.1"
    )
}

moduleDependencies {

    apps.presentation()
    di()
    predictions.presentation()
}

dependencies {

    implementation(libs.bundles.base)
    implementation(libs.bundles.compose)

    implementation(libs.androidx.activity)
    implementation(libs.androidx.ktx)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.lifecycle.viewModel)
    implementation(libs.koin.android)

    debugImplementation(libs.compose.uiTooling)

    testImplementation(libs.bundles.test.kotlin)
    testImplementation(libs.koin.test)
    androidTestImplementation(libs.bundles.test.android)
    androidTestImplementation(libs.compose.uiTest)
}