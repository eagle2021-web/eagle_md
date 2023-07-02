## Resource
```text
在 Spring 中，@Resource 注解根据以下顺序进行匹配：
name：使用指定的名称进行匹配，将与指定名称相匹配的 bean 注入。
type：如果没有指定 name 属性，则根*据*字段或方法的类型进行匹配。如果容器中存在唯一的类型匹配的 bean，则将其注入。如果存在多个类型匹配的 bean，则会抛出异常。
总的优先级顺序为：name > type。如果指定了 name 属性，则会首先按照名称进行匹配；如果没有指定 name 属性，则会按照类型进行匹配。
对于 @Resource 注解而言，默认情况下，它会使用 bean 的名称来进行匹配。如果希望按照类型进行匹配，可以使用 type 属性来指定需要注入的 bean 的类型。
```
## Autowire
```text
在 Spring 中，@Qualifier注解用于指定限定符来解决多个类型匹配的bean的注入问题。它结合 @Autowired 一起使用，提供更精确的依赖注入。
以下是使用 @Qualifier 注解的示例：
@Autowired
@Qualifier("beanQualifier")
private BeanType bean;
在上述示例中，@Autowired 注解用于标记需要注入的依赖对象，而 @Qualifier 注解则指定了要注入的bean的限定符（即bean的名称或其他定义的限定符），以解决多个类型匹配的bean的情况。
```