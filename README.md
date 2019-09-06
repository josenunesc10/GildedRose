# Gilded Rose

This is an Java approach for the Gilded Rose Refactoring Code Kata.

The specification of the exercise can be found [here](https://github.com/emilybache/GildedRose-Refactoring-Kata/blob/master/GildedRoseRequirements.txt)

## Implementation description

In order to try to follow the single-responsibility principle and facilitate extension, the item update logic was split into different classes (<Type>ItemHandler), each responsible for the update of a single item type.

The handlers are instantiated through a factory that detects the appropriate handler from the the name of the item to be processed.

## Building and Dependencies
The project uses Java 8  and is built with Maven. 

There is only one dependency, used in the tests.

```xml
<dependency>
  <groupId>junit</groupId>
  <artifactId>junit</artifactId>
  <version>4.12</version>
  <scope>test</scope>
</dependency>
```

## Tests
There are several unit tests written for different scenarios defined in the code kata specification.

The unit tests can be executed from the command lined with:

```bash
mvn test
```

## License
[MIT](https://choosealicense.com/licenses/mit/)
