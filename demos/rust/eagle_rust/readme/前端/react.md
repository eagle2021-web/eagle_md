1. antd引入报错可能是版本问题
2. ant不能引入css，需要引入min.css
3. <React.StrictMode>二次渲染

```tsx
interface Props {
    num: number
}

interface State {

}

class A extends React.Component<Props, State> {
    num: number;

    constructor(props: Props) {
        super(props);
        this.num = 1;
    }
}

```

## 理论知识

1. React是什么？
    1. 用于构建用户界面的JavaScript库
        1. 发送请求获取数据
        2. 处理数据（过滤、整理格式等）
        3. 操作DOM呈现页面 是一个将数据渲染为HTML试图的开源JavaScript库
2. 谁开发的？

- 由Facebook开发，且开源
- 起初由Facebook的软件工程师Jordan Walke创建
- 于2011年部署于Facebook的newsfeed
- 随后于2012年部署于Instagram 2013年5月宣布开源

##### 3.为什么要学？

```JavaScript
document.getElementById("app")
document.querySelector('#app')
document.getElementsByTagName('span')
```

1. 原生JavaScript操作DOM繁琐、效率低（DOM-API操作UI）
2. 使用JavaScript直接操作DOM，浏览器会进行大量的重绘重排
3. 原生JavaScript没有组件化编码方案，代码复用率低

##### 4.React的特点

1. 采用组件化模式、声明式编码，提高开发效率及组件复用率
2. 在React Native中可以使用React语法进行移动端开发
3. 使用虚拟DOM + 优秀的Diffing算法，尽量减少与真实DOM的交互

##### 5.学习React之前你要掌握的JavaScript基础知识

判断this的指向 class（类） ES6语法规范 npm包管理器 原型、原型链 数组常用方法 模块化

##### jsx, hello, React

```html
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<div id="test"></div>
<!--React-->
<script src="https://unpkg.com/react@16/umd/react.development.js" crossorigin></script>
<!--ReactDOM-->
<script src="https://unpkg.com/react-dom@16/umd/react-dom.development.js" crossorigin></script>
<!--babel-->
<script src="https://unpkg.com/@babel/standalone/babel.min.js"></script>
<script type="text/babel">
    //1.创建虚拟DOM
    const VD = <h1>hello, React!</h1> //此处一定不要写引号，因为不是字符串
    //2.渲染虚拟DOM到页面
    ReactDOM.render(VD, document.getElementById('test'));
    console.log(11111)
</script>
</body>
</html>
```

##### js, hello, React

```html
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<div id="test"></div>
<!--React-->
<script src="https://unpkg.com/react@16/umd/react.development.js" crossorigin></script>
<!--ReactDOM-->
<script src="https://unpkg.com/react-dom@16/umd/react-dom.development.js" crossorigin></script>
<script type="text/javascript">
    //1.创建虚拟DOM
    const VD = React.createElement('h1', {id: 'title'}, 'hello, World!');
    //2.渲染虚拟DOM到页面
    ReactDOM.render(VD, document.getElementById('test'));
    console.log(typeof VD);
    console.log(VD instanceof Object);
    console.log('虚拟DOM = ' + VD);
    const RD = document.getElementById('title');
    console.log('真实DOM = ' + RD);

    const d1 = [<div>111</div>, <div>222</div>]
    const V2 = <h1 style={{color: 'white'}}>aaa</h1>
    const d3 = <div>{d1}</div>
    /*
       关于虚拟DOM：
        1. 本质是Object类型的对象（一般对象）
        2. 虚拟DOM比较轻，真实DOM比较重，因为虚拟DOM是React内部使用，无需真实DOM那么多的属性
        3. 虚拟DOM最终会被React转化为真实的DOM，呈现在页面上
     */
</script>
</body>
</html>
```

##### 目前总结

1. jsx创建嵌套的虚拟DOM更加方便
2. 虚拟DOM打印在控制台是object类型

##### jsx

1.

##### xml

```xml

<student>
  <name>Tom</name>
  <age>18</age>
</student>
```

##### json

```json
{
  "name": "Tome",
  "age": 19
}
```

##### jsx语法规则：

1. 定义虚拟DOM时，不要写引号
2. 标签混入JS表达式时要用{}
3. 样式的类名指定不要用class，要用className
4. 内联样式，要用style={{key: value}}的形式去写
5. 只有一个跟标签
6. 标签必须闭合
7. 标签首字母
    1. 若小写字母开头， 则将标签转为html中同名元素，若html中无该标签对应的同名元素，则报错
    2. 若大写字母开头，react就去渲染对应的组件，若组件没有定义，则报错
8. 语句和表达式
    1. 表达式返回值
    2. 语句无

```html

<Good>book</Good>
<script>
    let a = 1; //表达式
</script>
```

#### 模块与组件、模块化与组件化的理解

##### 模块

1. 理解：向外提供特定功能的js程序，一般就是一个js文件
2. 为什么要拆解模块；随着业务逻辑增加，代码越来越复杂
3. 作用：服用js,简化js的编写，提高js运行效率

##### 组件

1. 理解：用来实现局部功能效果的代码和资源的集合（html/css/js/image等等）
2. 为什么：一个界面的功能更加复杂
3. 作用：复用编码，简化项目编写，提高运行效率

##### 模块化

当应用的js都以模块来编写的，这个应用就是一个模块化的应用

##### 组件化

当应用是以多组件的方式实现，这个应用就是一个组件化的应用



