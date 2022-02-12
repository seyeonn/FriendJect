<template>

  <div id="app">

    <div class="cursor" :style="cursor"></div>
    <!-- navbar  -->
    <nav class="navbar" >
      <ul class="my-navbar-nav">
        <li class="nav-item" @click="saveCanvas">
          
          <a class="my-nav-link" :href="url" download="canvas.png">SAVE</a>
        </li>
        <li class="nav-item " @click="initCanvas">
          
          <a class="my-nav-link" href="#">CLEAR ALL</a>
        </li>
        <li class="nav-item " @click="undo">
          
          <a class="my-nav-link" href="#">UNDO</a>
        </li>
        <li class="nav-item " @click="redo">
          
          <a class="my-nav-link" href="#">REDO</a>
        </li>
      </ul>
    </nav>


    <!-- canvas -->
      <canvas ref="canvas" id="myCanvas"></canvas>


    <!-- tools  -->
    <div class="tools">
      <template v-for="tool in tools">
        <div class="tools-item" @click="selectTool(tool)" :key="tool.name"><i :class="[tool.class,{'active':tool.name===currentTool}]">
            <span v-if="tool.name==='brush'">{{tool.name}}</span>
          </i>
        </div>
      </template>
      <div class="tools-item">SIZE:
        <input class="item-number" type="number" min="10" max="100" step="10" v-model.number="lineWidth">px
      </div>
      <div class="tools-item">
        <ul class="colors">COLOR:
          <li v-for="color in colors" :class="{'color-item':true,'active':color===currentColor}" :key="color"
            :style="{backgroundColor:color}" @click="colorPicker(color)">
            <i class="material-icons">✔</i>
          </li>
          <li class="color-item active">
            <!-- <b-icon-arrow-up></b-icon-arrow-up> -->
            <i class="material-icons" :class="{'active':random===currentColor}">
              🎨
            </i>
             <input type="color" id="randomInput" v-model="random" @change="randomColor">
            <div class="color-picker"></div>
          </li>
        </ul>
      </div>

    </div>

  </div>
  
</template>

<script src="https://rtcmulticonnection.herokuapp.com/dist/RTCMultiConnection.min.js"></script>
<script src="https://rtcmulticonnection.herokuapp.com/socket.io/socket.io.js"></script>
<script>
export default {
  name: 'WhiteBoard',
  data() {
    return {
    colors: ['#FFFFFF', '#000000', '#F5DA81', '#FFFF00', '#FFBF00'],
    tools: [{
      name: 'brush',
      class: 'material-icons brush-icon'
    }, {
      name: 'eraser',
      class: 'fas fa-eraser'
    }],
    currentColor: '#000000',
    currentTool: 'brush',
    random: '#cccccc',
    lineWidth: 10,
    lastX: 0,
    lastY: 0,
    isDrawing: false,
    isBrush: true,
    canvas: null,
    ctx: null,
    cursor: '',
    canvasArray: [],
    step: -1,
    url: '',
    initURL: ''
    }
  },
  mounted() {
    let vm = this
    vm.canvas = vm.$refs.canvas
    vm.canvas.addEventListener('mousemove', vm.mouseMove)
    vm.canvas.addEventListener('mousemove', vm.cursorMove)
    vm.canvas.addEventListener('mousedown', vm.mouseDown)
    vm.canvas.addEventListener('mouseup', vm.mouseUp)
    vm.canvas.addEventListener('mouseleave', vm.mouseLeave)
    window.addEventListener('resize', vm.resizeCanvas)
    window.addEventListener('onload', vm.initCanvas)
    vm.initCanvas()
  },
  methods: {
    initCanvas() {
      let vm = this
      vm.step = -1
      vm.ctx = vm.canvas.getContext('2d')
      vm.initURL = vm.canvas.toDataURL()
      vm.canvas.width = window.innerWidth
      vm.canvas.height = window.innerHeight
      vm.ctx.lineJoin = 'round'
      vm.ctx.lineCap = 'round'
      vm.ctx.strokeStyle = vm.currentColor
      vm.ctx.lineWidth = vm.lineWidth
      vm.ctx.fillStyle = '#E8E8E8'
      vm.ctx.fillRect(0, 0, vm.canvas.width, vm.canvas.height)
      vm.pushCanvas()
    },
    resizeCanvas() {
      let vm = this
      vm.canvas.width = window.innerWidth
      vm.canvas.height = window.innerHeight
      let image = new Image();
      image.src = vm.canvasArray[vm.step];
      image.onload = function () {
        vm.ctx.drawImage(image, 0, 0, vm.canvas.width, vm.canvas.height);
        vm.pushCanvas();
      }
    },
    pushCanvas() {
      let vm = this
      vm.step++
      if (vm.step < vm.canvasArray.length) { vm.canvasArray.length = vm.step; }
      vm.canvasArray.push(vm.canvas.toDataURL());
    },
    colorPicker(color) {
      let vm = this
      vm.currentColor = color
      vm.isBrush = true
      vm.setCurrentTool()
    },
    randomColor() {
      let vm = this
      let randomInput = document.querySelector('#randomInput')
      vm.currentColor = randomInput.value
      vm.isBrush = true
      vm.setCurrentTool()
    },
    setCurrentTool() {
      let vm = this
      if (vm.isBrush === true) {
        vm.currentTool = 'brush'
      }
    },
    selectTool(tool) {
      let vm = this
      vm.currentTool = tool.name
    },
    draw(e) {
      let vm = this

      vm.ctx.beginPath()
      vm.ctx.moveTo(vm.lastX, vm.lastY)
      vm.ctx.lineTo(e.offsetX, e.offsetY)

      if (vm.currentTool === 'brush') {
        vm.isBrush = true
        vm.ctx.strokeStyle = vm.currentColor
      } else {
        vm.isBrush = false
        vm.ctx.strokeStyle = '#e8e8e8'
      }
      vm.ctx.lineWidth = vm.lineWidth
      vm.ctx.lineJoin = 'round'
      vm.ctx.lineCap = 'round'
      vm.ctx.stroke()

      vm.lastX = e.offsetX
      vm.lastY = e.offsetY
    },
    mouseMove(e) {
      let vm = this
      if (!vm.isDrawing) return
      vm.draw(e)
    },
    mouseDown(e) {
      let vm = this
      vm.isDrawing = true
      vm.lastX = e.offsetX
      vm.lastY = e.offsetY
    },
    undo() {
      let vm = this
      if (vm.step > 0) {
        vm.step--
        let canvasPic = new Image()
        canvasPic.src = vm.canvasArray[vm.step]
        canvasPic.onload = () => {
          vm.ctx.drawImage(canvasPic, 0, 0)
        }
      }
      console.log('undo step', vm.step + ":" + ' length', vm.canvasArray.length)
    },
    redo() {
      let vm = this
      if (vm.step < vm.canvasArray.length - 1) {
        vm.step++;
        let canvasPic = new Image();
        canvasPic.src = vm.canvasArray[vm.step];
        canvasPic.onload = function () { vm.ctx.drawImage(canvasPic, 0, 0); }
        console.log('redo step', vm.step + ":" + ' length', vm.canvasArray.length)
      }
    },
    mouseUp() {
      let vm = this
      vm.isDrawing = false
      vm.pushCanvas()
      console.log('mouseDown step', vm.step + ":" + ' length', vm.canvasArray.length)
    },
    mouseLeave() {
      let vm = this
      vm.isDrawing = false
    },
    saveCanvas() {
      let vm = this
      let data = vm.canvasArray[vm.step]
      vm.url = data
    }

  },

}

</script>

<style lang="scss">
@import "@/assets/style/meetingroom/whiteboard.scss";
</style>