## 第二章 React面向组件编程

### 2.1 基本理解和使用

#### 2.1.1 使用React开发者工具调试

##### react_dev_tools

1. 百度网盘
2. f12 -> 箭头 -> components

### 2.1.2 组件

1. 函数式组件
2. 类式组件

```html
<!doctype html>
<html lang="en">
<body>
<div id="test"></div>
<script src="https://unpkg.com/react@16/umd/react.development.js" crossorigin></script>
<script src="https://unpkg.com/react-dom@16/umd/react-dom.development.js" crossorigin></script>
<script src="https://unpkg.com/@babel/standalone/babel.min.js"></script>
<script type="text/babel">
    //1.创建函数式组件
    function Demo() {
        return <h2>h2组件</h2>
    }

    //2.渲染组件到页面
    ReactDOM.render(<Demo/>, document.getElementById('test'))

    //3.创建类式组件
    class A extends React.Component {
        render() {
            return <h1>111</h1>
        }
    }

    //4.渲染组件到页面
    ReactDOM.render(<Demo/>, document.getElementById('test'))
</script>
</body>
</html>
```

##### 执行了ReactDOM.render(Demo, ...) 之后， 发生了什么？

1. React解析组件标签，找到了MyC组件
2. 发现组件是使用函数定义的，随后调用该函数，将返回的虚拟DOM转为真实DOM，随后呈现在页面上

##### 执行了ReactDOM.render(A, ...) 之后， 发生了什么？（类组件）

1. React解析组件标签，找到了A组件
2. 发现组件是使用类定义的，随后new出来该类的实例，并通过该实例调用到原型上的render方法
3. 将render返回的虚拟DOM转为真实DOM，随后呈现在页面中

#### 2.2 组件实例的三大核心属性1：state

##### 2.2.1 效果

##### 2.2.2 理解

1. state是组件对象最重要的属性，值是对象（可以包含多个key-value的组合）
2. 组件被称为"状态机"，通过更新组件的state来更新对应的页面显示（重新渲染组件）

```javascript
changeWeather()
{
    // changeWeather放在哪里？ ---- Weather的原型对象上，供实例使用
    // 由于changeWeather是作为onClick的回调，所以不是通过实例调用的，是直接调用
    // 类中的方法默认开启局部的严格模式，所以changeWeather中this/undefined
    console.log(this)
}
```

##### 2.2.4 强烈注意

1. 组件中render方法中的this为组件实例对象
2. 组件自定义的方法中this为undefined，如何解决？
    1. 强制绑定this: 通过函数对象的bind()
    2. 箭头函数
3. 状态数据，不能直接修改更新

#### 2.3 组件三大核心属性2：props

#### 2.5

##### 2.5.1 refs

```javascript
class A extends React.Component<Props, {
    num: number
}> {
    ref2: RefObject<HTMLButtonElement>;
    ref3: HTMLButtonElement | null;
    ref4: HTMLButtonElement | null;

    constructor(props: Props) {
        super(props);
        // createRef调用后可以返回一个容器，该容器可以存储被ref所标识的节点
        this.ref2 = React.createRef();
        this.ref3 = null;
        this.ref4 = null;
        this.state = {
            num: 1
        }
    }

    showData = () => {
        console.log(this["refs"].input1)
    }
    showData2 = (user: any) => {
        console.log('user = ', user);
        console.log(this.ref2.current?.innerText)
    }
    showData3 = (user: any) => {
        console.log(this.ref3?.innerText)
    }
    addNum = () => {
        this.setState({
            num: this.state.num + 1
        })
    }
    bind_cur_node = (cur_node: any) => {
        this.ref4 = cur_node
        console.log('cur_node4 = ' + cur_node)
    }

    render() {
        return <div>
            <input ref="input1" type="text" placeholder={"点击按钮提示数据"}/>
            &nbsp;
            <button onClick={this.showData}>点我提示左侧数据</button>
            &nbsp;
            <button onClick={this.showData2} ref={this.ref2}>点我提示左侧数据222</button>
            &nbsp;
            <button onClick={this.showData3} ref={(cur_node) => {
                // 第一次传递null, 清空状态；每次dom update 会调用2次
                this.ref3 = cur_node
                console.log('cur_node3 = ' + cur_node)
            }}>
                点我提示左侧数据333
            </button>
            <button onClick={this.showData3} ref={this.bind_cur_node}>
                点我提示左侧数据4
            </button>
            &nbsp;
            <button onClick={this.addNum}>++1</button>
        </div>
    }
}
```

```javascript
/*
 1.通过onXxx属性指定事件处理函数
    a) React使用的是自定义（合成）事件，而不是使用的原生DOM事件 --为了更好的兼容性
    b) React中的事件是通过事件委托方式处理的（委托给组件最外层的元素）--为了高效
 2.通过event.target得到发生事件的DOM元素对象 --不要过度使用ref
 */

```

#### 非受控组件

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
<script src="https://unpkg.com/react@16/umd/react.development.js" crossorigin></script>
<script src="https://unpkg.com/react-dom@16/umd/react-dom.development.js" crossorigin></script>
<script src="https://unpkg.com/@babel/standalone/babel.min.js"></script>
<script type="text/babel">


    class Login extends React.Component {
        handleSubmit = (event) => {
            event.preventDefault() // 阻止默认事件--表单提交
            const {username, password} = this
            alert(`你输入的用户名是：${username.value}, 你输入的密码是${password.value}`)
        }

        render() {
            return (
                    <form action="http://www.atguigu.com" onSubmit={this.handleSubmit}>
                        用户名： <input type="text" ref={c => this.username = c} name={"username"}/>
                        密码：<input type="password" ref={c => this.password = c} name="password"/>
                        <button>登录</button>
                    </form>
            )
        }

    }


    ReactDOM.render(<Login/>, document.getElementById("test"))
</script>
</body>
</html>
```

#### 受控组件

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
<script src="https://unpkg.com/react@16/umd/react.development.js" crossorigin></script>
<script src="https://unpkg.com/react-dom@16/umd/react-dom.development.js" crossorigin></script>
<script src="https://unpkg.com/@babel/standalone/babel.min.js"></script>
<script type="text/babel">

    class Login extends React.Component {
        state = {
            "username": "",
            "password": ""
        }

        saveUsername = (event) => {
            this.setState({
                **this.state,
                "username": event.target.value,
            })
        }
        savePassword = (event) => {
            this.setState({
                **this.state,
                "password": event.target.value
            })
        }

        render() {
            return (
                    <form action="http://www.atguigu.com" onSubmit={this.handleSubmit}>
                        用户名： <input onChange={this.saveUsername} type="text" name={"username"}/>
                        密码：<input onChange={this.savePassword} type="password" name="password"/>
                        <button>登录</button>
                    </form>
            )
        }
    }

    ReactDOM.render(<Login/>, document.getElementById("test"))
</script>
</body>
</html>
```

#### 柯里化函数

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
<script src="https://unpkg.com/react@16/umd/react.development.js" crossorigin></script>
<script src="https://unpkg.com/react-dom@16/umd/react-dom.development.js" crossorigin></script>
<script src="https://unpkg.com/@babel/standalone/babel.min.js"></script>
<script type="text/babel">
    /*
        高阶函数：1.参数是函数 或者 2.返回值是函数
            例如：Promise, setTimeout, arr.map等
        函数的柯里化： 通过函数调用继续返回函数的方式，实现多次接收参数最后统一处理的函数编码形式
            
     */
    class Login extends React.Component {
        state = {
            "username": "",
            "password": ""
        }
        saveFormData = (dataName) => {
            return (event) => {
                this.setState({
                    [dataName]: event.target.value
                })
            }

        }

        render() {
            return (
                    <form action="http://www.atguigu.com" onSubmit={this.handleSubmit}>
                        用户名： <input onChange={this.saveUsername} type="text" name={"username"}/>
                        密码：<input onChange={this.savePassword} type="password" name="password"/>
                        <button>登录</button>
                    </form>
            )
        }
    }

    ReactDOM.render(<Login/>, document.getElementById("test"))
</script>
</body>
</html>
```


#### 生命周期回调函数
##### 生命周期钩子函数 or 生命周期函数 or 生命周期钩子
```html
<!doctype html>
<html lang="en">
<body>
<div id="test"></div>
<script src="https://unpkg.com/react@16/umd/react.development.js" crossorigin></script>
<script src="https://unpkg.com/react-dom@16/umd/react-dom.development.js" crossorigin></script>
<script src="https://unpkg.com/@babel/standalone/babel.min.js"></script>
<script type="text/babel">

    class Login extends React.Component {
        constructor(props) {
            super(props);
            this.state = {
                opacity: 1,
                direct: 1
            }
        }

        componentDidMount() {
            this.timer = setInterval(()=> {
                let {direct, opacity} = this.state;
                direct = opacity >= 1 || opacity <= 0 ? -direct : direct;
                opacity += 0.1 * direct;
                this.setState({
                    direct,
                    opacity
                })
            }, 200)

        }
        close = () => {
            // clearInterval(this.timer)
            ReactDOM.unmountComponentAtNode(document.getElementById("test"))
        }
        componentWillUnmount() {
            clearInterval(this.timer)
        }

        render() {
            return (
                <div>
                    <h2 style={{opacity: this.state.opacity}}>React</h2>
                    <button onClick={this.close}>卸载组件</button>
                </div>
            )
        }
    }

    ReactDOM.render(<Login/>, document.getElementById("test"))
</script>
</body>
</html>
```