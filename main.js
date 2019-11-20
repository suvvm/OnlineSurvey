import Vue from 'vue'
import App from './App'
import Vant from 'vant'
import lrz from 'lrz'
import VueCookies from 'vue-cookies'
import pinyin from 'js-pinyin'

Vue.use(VueCookies)
Vue.use(Vant)

Vue.config.productionTip = false

App.mpType = 'app'

const app = new Vue({
    ...App
})
app.$mount()
