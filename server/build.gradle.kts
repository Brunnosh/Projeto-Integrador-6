plugins { application; java }
application {
    // ajuste quando criar sua classe Main
    mainClass.set("br.puc.pi6.server.Main")
}
dependencies {
    implementation(project(":shared"))
    // logs etc. (opcional)
    // implementation("ch.qos.logback:logback-classic:1.5.7")
}
