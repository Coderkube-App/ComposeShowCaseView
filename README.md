# ComposeShowCaseView

A lightweight Jetpack Compose library for creating beautiful app onboarding and feature highlight showcases. Highlight UI elements with a dimmed overlay, title, subtitle, and optional animations — perfect for first-time user walkthroughs.

## Features

- **Multiple showcase styles** — Simple or animated, rounded or rectangle highlights
- **Sequential walkthroughs** — Show multiple targets one by one using `index`
- **Manual or automatic mode** — Tap the target to advance, or auto-advance with a configurable delay
- **Persistent state** — Use a `key` to show the showcase only once per user
- **Fully customizable** — Title, subtitle, colors, blur opacity, and delay per target
- **Jetpack Compose native** — Built with Compose Canvas, no XML required

## Installation

Add JitPack to your root `settings.gradle.kts` (or `settings.gradle`):

```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

Add the dependency in your app module's `build.gradle.kts`:

```kotlin
dependencies {
    implementation("com.github.Coderkube-App:ComposeShowCaseView:1.0.1")
}
```

## Usage

### 1. Register showcase targets

Use `onGloballyPositioned` on each composable you want to highlight. Store each target in a `SnapshotStateMap` with a unique key:

```kotlin
val targets = remember { mutableStateMapOf<String, ShowcaseProperty>() }

Icon(
    imageVector = Icons.Default.Favorite,
    contentDescription = "Like",
    modifier = Modifier.onGloballyPositioned { coordinates ->
        targets["like"] = ShowcaseProperty(
            index = 1,
            coordinates = coordinates,
            title = "Like Post",
            subTitle = "Tap here to like this post",
            showCaseType = ShowcaseType.ANIMATED_ROUNDED
        )
    }
)
```

### 2. Show the showcase overlay

Place `ShowCaseTarget` at the root of your screen (e.g. inside a `Scaffold` or `Box`):

```kotlin
@Composable
fun MyScreen() {
    val targets = remember { mutableStateMapOf<String, ShowcaseProperty>() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Home") },
                modifier = Modifier.onGloballyPositioned { coordinates ->
                    targets["toolbar"] = ShowcaseProperty(
                        index = 0,
                        coordinates = coordinates,
                        title = "Toolbar",
                        subTitle = "Access navigation and actions from here",
                        showCaseType = ShowcaseType.SIMPLE_ROUNDED
                    )
                }
            )
        }
    ) { padding ->
        // Your screen content with more targets...
    }

    ShowCaseTarget(
        targets = targets,
        isEnableAutoShowCase = true,
        key = "home_showcase"
    ) {
        // Called when all showcases are completed
        Log.d("Showcase", "Walkthrough finished!")
    }
}
```

### 3. Showcase types

| Type | Description |
| --- | --- |
| `SIMPLE_ROUNDED` | Circular cutout with a dimmed background |
| `SIMPLE_RECTANGLE` | Rectangular cutout with a dimmed background |
| `ANIMATED_ROUNDED` | Circular cutout with pulsing ripple animation |
| `ANIMATED_RECTANGLE` | Rectangular cutout with pulsing ripple animation |

### Interaction modes

| Mode | Behavior |
| --- | --- |
| Manual (`isEnableAutoShowCase = false`) | User taps the highlighted target to move to the next step |
| Automatic (`isEnableAutoShowCase = true`) | Advances automatically after `showcaseDelay` milliseconds |

## All Attributes

| Attribute | Description | Default |
| --- | --- | --- |
| `index` | Set index to show showcase one by one | None |
| `coordinates` | Components coordinates for showcase | None |
| `title` | Showcase title | None |
| `titleColor` | Color for title | `White` |
| `subTitle` | Showcase subtitle | None |
| `subTitleColor` | Color for subtitle | `White` |
| `showCaseType` | Pass type of showcase (SIMPLE_ROUNDED, SIMPLE_RECTANGLE, ANIMATED_ROUNDED, ANIMATED_RECTANGLE) | `SIMPLE_ROUNDED` |
| `blurOpacity` | Pass opacity to blur background | `0.08f` |
| `isEnableAutoShowCase` | To manage showcase automatically | `false` |
| `showcaseDelay` | Delay in-between showcase for automatic showcase view | `2000` |
| `key` | To manage if showcase already shown or not | None |

> `isEnableAutoShowCase` and `key` are parameters on `ShowCaseTarget`. All other attributes belong to `ShowcaseProperty`.

## Contributing

Contributions are welcome! To get started:

1. Fork the repository.
2. Create a feature branch (`git checkout -b feature/my-feature`).
3. Commit your changes (`git commit -m 'Add my feature'`).
4. Push to the branch (`git push origin feature/my-feature`).
5. Open a Pull Request.

Please keep changes focused and include a clear description in your PR. For bugs or feature requests, open an [issue](https://github.com/Coderkube-App/ComposeShowCaseView/issues) first.

## License

```
Copyright 2026 Coderkube-App

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

See [LICENSE](LICENSE) for the full license text.
