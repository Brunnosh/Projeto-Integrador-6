plugins { application; java }
application {
    // ajuste quando criar seu launcher
    mainClass.set("br.puc.pi6.client.Main")
}
dependencies {
    implementation(project(":shared"))

    // Adicione LibGDX quando for usar
    // implementation("com.badlogicgames.gdx:gdx:1.13.5")
    // implementation("com.badlogicgames.gdx:gdx-backend-lwjgl3:1.13.5")
    // runtimeOnly("com.badlogicgames.gdx:gdx-platform:1.13.5:natives-desktop")
}
