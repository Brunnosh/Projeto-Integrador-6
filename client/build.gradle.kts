plugins { application; java }
application {
    // ajuste quando criar seu launcher
    mainClass.set("br.puc.pi6.client.Launcher")
}
dependencies {
    implementation(project(":shared"))

    // LibGDX (desktop)
    implementation("com.badlogicgames.gdx:gdx:1.13.5")
    implementation("com.badlogicgames.gdx:gdx-backend-lwjgl3:1.13.5")
    implementation ("com.badlogicgames.gdx:gdx-box2d:1.13.5")
    runtimeOnly ("com.badlogicgames.gdx:gdx-box2d-platform:1.13.5:natives-desktop")
    runtimeOnly("com.badlogicgames.gdx:gdx-platform:1.13.5:natives-desktop")

    // fontes TTF
    implementation("com.badlogicgames.gdx:gdx-freetype:1.13.5")
}

sourceSets {
    named("main") {
        resources.srcDir("assets")
    }
}