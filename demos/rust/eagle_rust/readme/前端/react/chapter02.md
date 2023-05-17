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
      console.log('cur_node4 = ' +cur_node)
   }
   render() {
      return <div>
         <input ref="input1" type="text" placeholder={"点击按钮提示数据"}/>&nbsp;
         <button onClick={this.showData}>点我提示左侧数据</button>&nbsp;
         <button onClick={this.showData2} ref={this.ref2}>点我提示左侧数据222</button>&nbsp;
         <button onClick={this.showData3} ref={(cur_node) => {
            // 第一次传递null, 清空状态；每次dom update 会调用2次
            this.ref3 = cur_node
            console.log('cur_node3 = ' +cur_node)
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
