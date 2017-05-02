// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue';
import App from './App';
import Fabric from 'fabric';
import VeeValidate from 'vee-validate';
import router from './router';
import resource from 'vue-resource';
import vSelect from 'vue-select'

Vue.component('v-select', vSelect)

Vue.config.productionTip = false;

Vue.use(VeeValidate);

Vue.use(resource);

Vue.use(fabric);
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App }
});

