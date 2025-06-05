# Appium + POM Mobile Automation Framework

Framework de automatización de pruebas móviles utilizando el patrón Page Object Model (POM).
Este proyecto está para facilitar la escritura de pruebas legibles y mantenibles.
Además, está integrado con BrowserStack para pruebas en la nube y soporta dispositivos Android e iOS.

## 🚀 Características

- Automatización de pruebas móviles usando Appium
- Estructura basada en Page Object Model (POM)
- Integración con Cucumber y lenguaje Gherkin
- Soporte para ejecución local y en la nube (BrowserStack)
- Manejo de logs y reportes automáticos
- Ejecución con Maven y JUnit


## 🛠️ Tecnologías Utilizadas

- **Lenguaje de programación**: Java
- **Frameworks de automatización**:
    - [Appium](https://appium.io/)
    - [Cucumber](https://cucumber.io/)
    - [JUnit](https://junit.org/)
- **Herramientas adicionales**:
    - [Maven](https://maven.apache.org/)
    - [BrowserStack](https://www.browserstack.com/)

## 📂 Estructura del Proyecto

```
aut-mobile-pom/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── pages/            # Clases de Page Object Model (pantallas)
│   └── test/
│       ├── java/
│       │   ├── runners/          # Clases que configuran la ejecución de Cucumber
│       │   ├── steps/            # Step Definitions para los escenarios Gherkin
│       │   └── utils/            # Utilitarios generales
│       └── resources/
│           └── features/         # Archivos .feature con los escenarios
├── pom.xml                       # Configuración de dependencias Maven
└── README.md                     # Documentación del proyecto
```

## 📋 Instalación

### Clonar el Repositorio

```
git clone https://github.com/wpmontenegro/aut-mobile-pom.git
cd aut-mobile-pom
```

### Instalación de Dependencias

```
mvn clean install -DskipTests
```

## ▶️ Ejecución de pruebas

### Prerrequisitos

- Appium Server (para pruebas locales)
- Cuenta en BrowserStack (para pruebas en la nube)
- Descargar [**MyDemoApp**](https://github.com/saucelabs/my-demo-app-rn/releases/)

### Pruebas locales

1. Iniciar el servidor de Appium en el terminal: ```appium```
2. Descarga la app y establece la ruta del apk dentro del archivo `properties`.
```
appium.app:ruta_del_aplicativo
```
3. Ejecutar las pruebas:
```
mvn test -Dplatform={PLATFORM}
```

### Pruebas en BrowserStack (nube)

1. Sube la aplicación a **BrowserStack** y verifica la configuración en el archivo `properties`.
```
browserstack.active:true
```
2. Establezca las **Credenciales de BrowserStack/SauceLabs** como variables de entorno:
```
export BROWSERSTACK_USERNAME=tu_usuario
export BROWSERSTACK_ACCESS_KEY=tu_clave
```
3. Ejecutar las pruebas:
```
mvn test -Dplatform={PLATFORM} -Dapp={APP_URL}
```

## 🔧 Configuración

### Parámetros

`platform` (requerido):

Specifies the mobile platform to test. Supported values:

```
android (para dispositivos Android)
ios (para dispositivos  iOS)
```

### Properties

Este proyecto utiliza archivos `.properties` para gestionar configuraciones clave y facilitar la ejecución en distintos entornos (local, remoto, CI/CD).
Los archivos de configuración se encuentran en:

```
src/test/resources/
├── application.properties  # Configuraciones generales para ejecución local y en la nube con Appium
├── android.properties      # Configuraciones para ejecución en Android
├── ios.properties          # Configuraciones para ejecución en iOS
```

## 📊 Reportes de Pruebas

Los reportes de las ejecuciones se generan automáticamente en formato HTML y se almacenan en la carpeta `target/cucumber-reports/`.
Para visualizar un reporte:

```
open target/cucumber-reports/Cucumber.html
```

## 📬 Contacto

Para preguntas o sugerencias, puedes contactarme a través de:

- Correo electrónico: wmontenegro@outlook.com.pe
- LinkedIn: [**wmontenegro**](https://www.linkedin.com/in/wmontenegro)
- GitHub: [**wpmontenegro**](https://github.com/wpmontenegro)