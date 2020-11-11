# ts-es-mapping-plugin

![Build](https://github.com/wulkanat/ts-es-mapping-plugin/workflows/Build/badge.svg)
[![Version](https://img.shields.io/jetbrains/plugin/v/15371.svg)](https://plugins.jetbrains.com/plugin/15371)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/15371.svg)](https://plugins.jetbrains.com/plugin/15371)

<!-- Plugin description -->
Plugin to assist with annotating interfaces that are parsed by the ES Mapping generator
from the [StApps Core-tools](https://gitlab.com/openstapps/core-tools)
<!-- Plugin description end -->

## Features

### Supported tags
| Annotation        | Description                                                                                                             | Parameters                                                                       |
|-------------------|-------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------|
| `@aggregatable`   | used for generating of aggregations of the field if the core schema is used to put data into a database/key-value store | whether the property is being used on the top type or across all types: `global` |
| `@float`          | number field is interpreted as float                                                                                    |                                                                                  |
| `@indexable`      | marks the type as indexable if the core schema is used to put data into a database/key-value store                      |                                                                                  |
| `@integer`        | number field is interpreted as integer                                                                                  |                                                                                  |
| `@keyword`        | string field is interpreted as keyword                                                                                  |                                                                                  |
| `@sortable`       | field is sortable if the core schema is used to put data into a database/key-value store                                | sort method to be used: `ducet`, `price`, `distance`                             |
| `@text`           | string field is interpreted as text                                                                                     |                                                                                  |
| `@filterable`     | non-object/nested field is filterable if the core schema is used to put data into a database/key-value store            |                                                                                  |
| `@inheritTags`    | inherit all tags from another field                                                                                     | `[SCThingType]::[field]`                                                         |

* `@integer` and `@float` only applies to `number`
* `@text` and `@keyword` only applies to `string`
* `@sortable ducet` only applies to `string`
* `@sortable price` only applies to `number`
* Interfaces only accept `@indexable`
* Fields accept everything else except `@indexable`

*Arrays are ignored and their element type is used*

## Installation

- Using IDE built-in plugin system:
  
  <kbd>Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "ts-es-mapping-plugin"</kbd> >
  <kbd>Install Plugin</kbd>
  
- Manually:

  Download the [latest release](https://github.com/wulkanat/ts-es-mapping-plugin/releases/latest) and install it manually using
  <kbd>Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>


---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template
