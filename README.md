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
## 1.1 Dynamic Lists
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