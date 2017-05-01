// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import Router from './router'
import Vuetify from 'vuetify'
import VeeValidate from 'vee-validate';
import VueResource from 'vue-resource'

Vue.use(VueResource);
Vue.use(Vuetify);
Vue.use(VeeValidate);


Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  render: h => h(App),
  router: Router
})
