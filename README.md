# Automation Test Mobile Android/iOS
Framework de automatización mobile con Page Object Model usando Java + Cucumber + Maven + Junit + BrowserStack.
El framework soporta Android y iOS.

---

## 🚀 Características

- Automatización de pruebas móviles usando Appium
- Estructura basada en Page Object Model (POM)
- Integración con Cucumber y lenguaje Gherkin
- Soporte para ejecución local y en la nube (BrowserStack)
- Manejo de logs y reportes automáticos
- Ejecución con Maven y JUnit

---

## 🛠️ Tecnologías utilizadas

- Java 11+
- Appium
- Cucumber
- Gherkin
- Maven
- BrowserStack

---

## 📁 Estructura del proyecto

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

---

## ▶️ Ejecución de pruebas

### Ejecución local

Asegúrate de tener Appium Server ejecutándose localmente y un emulador o dispositivo conectado.

```
mvn clean verify
```

### Ejecución en BrowserStack (nube)

Para ejecutar pruebas en la nube (por ejemplo, en iOS), exporta las credenciales de BrowserStack como variables de entorno:

```
export BROWSERSTACK_USERNAME=tu_usuario
export BROWSERSTACK_ACCESS_KEY=tu_clave
```

Luego ejecuta las pruebas con los parámetros necesarios:

```
mvn clean verify -Dplatform={PLATFORM} -Dapp={CLOUD_APP}
```
🔐 Las credenciales no deben almacenarse en archivos del repositorio. Usa siempre variables de entorno para mantenerlas seguras.