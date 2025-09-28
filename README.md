# Dynamic Invoker

A minimal Java project to dynamically invoke any class method using reflection, based on input from a file. (for learning java reflections).

## How It Works?

1. **Input:**
    The input is read and parsed from `in.txt` file in the project root with the following format:
    ```
    <fully-qualified-class-name> <method-name>
    <parameter-type>:<parameter-value>
    <parameter-type>:<parameter-value>
    ...
    END
    ```
    Example:
    ```
    java.lang.Math max
    integer:10
    integer:20
    END
    ```

2. **Execution:**  
    The program will:
    - Parse the input file
    - Load the specified class and method using reflection
    - Invoke the method with the given parameters
    - Print the result to the console

## Supported Parameter Types

- integer
- long
- short
- float
- double
- boolean
- string

## Example

```
java.lang.String substring
string:"Hello, world!"
integer:7
END
```

This will invoke `"Hello, world!".substring(7)` and print `world!`.

---

**Note:**  
I made it for learning and experimenting with Java reflection.
