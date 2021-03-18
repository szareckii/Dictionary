import org.gradle.api.JavaVersion

object Config {
    const val application_id = "com.szareckii.dictionary"
    const val compile_sdk = 30
    const val min_sdk = 21
    const val target_sdk = 30
    val java_version = JavaVersion.VERSION_1_8
}

object Releases {
    const val version_code = 3
    const val version_name = "3.0"
}

object Modules {
    const val app = ":app"
    const val core = ":core"
    const val model = ":model"
    const val repository = ":repository"
    const val utils = ":utils"
    // Features
    const val historyScreen = ":historyscreen"
}

object Versions {
    // Tools
    const val multidex = "1.0.3"

    // Design
    const val appcompat = "1.2.0"
    const val material = "1.3.0"

    // Kotlin
    const val core = "1.3.2"
    const val stdlib = "1.4.30"

    // Coroutines
    const val coroutinesCore = "1.4.1"
    const val coroutinesAndroid = "1.4.1"

    // Retrofit
    const val retrofit = "2.6.0"
    const val converterGson = "2.6.0"
    const val interceptor = "3.12.1"
    const val adapterCoroutines = "0.9.2"

    // Koin
    const val koinAndroid = "2.0.1"
    const val koinViewModel = "2.0.1"

    // Glide
    const val glide = "4.9.0"
    const val glideCompiler = "4.9.0"

    // Picasso
    const val picasso = "2.5.2"

    // Room
    const val roomKtx = "2.3.0-beta02"
    const val runtime = "2.3.0-beta02"
    const val roomCompiler = "2.3.0-beta02"

    // Swiperefreshlayout
    const val swiperefreshlayout = "1.1.0"

    // GooglePlay
    const val googlePlay = "1.6.3"

    // Test
    const val jUnit = "4.13.1"
    const val runner = "1.3.0"
    const val espressoCore = "3.3.0"
}


object Tools {
    const val multidex = "com.android.support:multidex:${Versions.multidex}"
}

object Design {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
}

object Kotlin {
    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.stdlib}"
    const val coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesCore}"
    const val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesAndroid}"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val converter_gson = "com.squareup.retrofit2:converter-gson:${Versions.converterGson}"
    const val adapter_coroutines =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.adapterCoroutines}"
    const val logging_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.interceptor}"
}

object Koin {
    const val koin_android = "org.koin:koin-android:${Versions.koinAndroid}"
    const val koin_view_model = "org.koin:koin-android-viewmodel:${Versions.koinViewModel}"
}

object Glide {
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glide_compiler = "com.github.bumptech.glide:compiler:${Versions.glideCompiler}"
}

object Picasso {
    const val picasso = "com.squareup.picasso:picasso:${Versions.picasso}"
}

object Room {
    const val runtime = "androidx.room:room-runtime:${Versions.runtime}"
    const val compiler = "androidx.room:room-compiler:${Versions.roomCompiler}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.roomKtx}"
}

object SwipeRefreshLayout {
    const val swiperefreshlayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swiperefreshlayout}"
}
object GooglePlay {
    const val googlePlay = "com.google.android.play:core:${Versions.googlePlay}"
}

object TestImpl {
    const val junit = "junit:junit:${Versions.jUnit}"
    const val runner = "androidx.test:runner:${Versions.runner}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
}

