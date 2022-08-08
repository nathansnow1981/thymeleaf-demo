# Thymeleaf Help

- [Full Tutorial](https://www.thymeleaf.org/doc/tutorials/2.1/thymeleafspring.html)
- [Forms](https://spring.io/guides/gs/handling-form-submission/)
- [Error Messages](https://www.baeldung.com/spring-thymeleaf-error-messages)
- [CRUD Operations](https://www.baeldung.com/spring-boot-crud-thymeleaf)
- [Redirects](https://www.baeldung.com/spring-redirect-and-forward)
- [Iterating](https://www.baeldung.com/thymeleaf-iteration)
- [Lists](https://www.baeldung.com/thymeleaf-lists-utility)
- [Comparators](https://springhow.com/thymeleaf-comparators-and-equality/)
- [StringUtils Javadoc](https://www.thymeleaf.org/apidocs/thymeleaf/2.0.19/org/thymeleaf/util/StringUtils.html)
- [Expressions](https://www.thymeleaf.org/doc/articles/standarddialect5minutes.html)
- [Expressions](https://www.baeldung.com/spring-thymeleaf-3-expressions)

## Some common items

```
- ${...} : Variable expressions.
- *{...} : Selection expressions.
- #{...} : Message (i18n) expressions.
- @{...} : Link (URL) expressions.
- ~{...} : Fragment expressions.
```

Use | pipes | for concatenation
```xml 
" Maximum file size: ${sizeLimit}" 	<!-- Won't work -->
"| Maximum file size: ${sizeLimit}|" 	<!-- Works -->
```

Use `th:` to convert html elements to Thymeleaf elements
```html
<link href="/css/site.css" rel="stylesheet" >
<link th:href="@{~/css/site.css}" rel="stylesheet" >

<p>Some static text</p>
<!-- "dynamicText", "Text passed to the view template via the model" -->
<p th:text="${dynamicText}"></p>
```
```html
<a class="all my classes here" href="https://my-url-here.com" >Manually type the text here</a>
<a th:class="${myClasses}" th:href="@{myUrl}" th:text="${myText}"></a>
```