# Thymeleaf Demo

## 1.0 - Dynamic Text

1. [index.html](src/main/resources/templates/index.html) demonstrates using `th:text="${myVariabe}"` to inject values dynamically into the page.
    ```html
    <body>
        <p>This is a normal static content</p>
        <p th:text="${dynamicContent}">We don't need any text in here</p>
    </body>
    ```
2. [WebController.java](src/main/java/com/example/thymeleaf/WebController.java) contains `GET` mapping for requests to the index page. It takes a `Model` as an input so that we can pass attributes to the model which will then be passed to the index page where we can render its content.
   ```java 
   @GetMapping({"/",""})
    public String index(Model model){
        model.addAttribute("siteTitle","Thymeleaf Demo");
        model.addAttribute("dynamicContent","This is some dynamic content");
        return "/index";
    }
   ```
## 1.1 - Dynamic Lists
1. [index.html](src/main/resources/templates/index.html) shows how we can use `th:each"` to iterate through a list called "items", and then create a new `<li>` element for each list item. Then we set the value of the list item element to the value of that list item.
   ```html
   <ul>
      <li th:each="item : ${items}" th:text="${item}"></li>
   </ul>
   ```
2. [WebController.java](src/main/java/com/example/thymeleaf/WebController.java) shows how we add a list of items (in this case just strings) to the model
   ```java 
   @GetMapping({"/",""})
    public String index(Model model){
        model.addAttribute("siteTitle","Thymeleaf Demo");
        model.addAttribute("dynamicContent","This is some dynamic content");
        //Add the list of items
        model.addAttribute("items", List.of("Item A", "Item B", "Item C", "Item D"));
        return "/index";
    }
   ```
## 1.2 - Dynamic Tables
1. [index.html](src/main/resources/templates/index.html) shows how we can use `th:each"` to iterate through a list again, but this time the list is called "students", and contains [Student](src/main/java/com/example/thymeleaf/Student.java) objects.
   ```html
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
        </tr>
        <tr th:each="student : ${students}">
            <td th:text="${student.id}" ></td>
            <td th:text="${student.name}" ></td>
            <td th:text="${student.email}" ></td>
        </tr>
    </table>
   ```
2. In [WebController.java](src/main/java/com/example/thymeleaf/WebController.java) we add a list of [Student](src/main/java/com/example/thymeleaf/Student.java) objects to the model.
   ```java 
   model.addAttribute("students", List.of(
          new Student(1, "Tommy Tester", "t.tester@gmail.com"),
          new Student(2, "Harry Hacker", "hackerh@gmail.com"),
          new Student(3, "Jacky Java", "jj@gmail.com"),
          new Student(4, "Devon Developer", "devon.d@gmail.com")
   ));
   ```
   The index page will now show the following table ![student-table.png](screenshots/student-table.png)

## 2.0 - Fragments
1. A new [fragments](src/main/resources/templates/fragments) directory holds a new fragment [head](src/main/resources/templates/fragments/head.html). This fragment contains only the `<head>` element content, which now also includes the bootstrap cdn link.
2. The [index](src/main/resources/templates/index.html) now has its head element replaced with the following syntax, which simply instructs it to replace this head element with the head element that can be found in the `fragments/head` file.
   ```html
   <head th:replace="fragments/head"></head>
   ```
   When thymeleaf looks at `fragments/head`, it can see that it is a fragment by the `th:fragment="head"` declaration.
   Running the program now shows the table looking like below:
   ![bootstrap-students-table.png](screenshots/bootstrap-students-table.png)


## Resources
### Standard Expressions
```
- ${...} : Variable expressions.
- *{...} : Selection expressions.
- #{...} : Message (i18n) expressions.
- @{...} : Link (URL) expressions.
- ~{...} : Fragment expressions.
```
[*Standard expression documentation*](https://www.thymeleaf.org/doc/articles/standarddialect5minutes.html)