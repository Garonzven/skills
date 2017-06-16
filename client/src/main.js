import Vue from 'vue';
import Vuex from 'vuex';
import Resource from 'vue-resource';
import VeeValidate from 'vee-validate';
import vSelect from 'vue-select';
import App from './App';
import router from './router';

import Auth from './packages/auth/Auth.js';

Vue.use(Vuex);
Vue.use(Resource);
Vue.use(Auth);
Vue.use(VeeValidate);
Vue.component('v-select', vSelect);


//Vue.http.options.root = 'http://13.92.199.15:38967/WebSkillsGW7/webresources/'

router.beforeEach(
  (to, from, next) => {
    if(to.matched.some(record => record.meta.forVisitors)) {
      if(Vue.auth.isAuthenticated()) {
        next({
          path: '/'
        })
      } else next()
    }
    else if(to.matched.some(record => record.meta.forAuth)) {
      if( ! Vue.auth.isAuthenticated()) {
        next({
          path: '/login'
        })
      } else next()
    }
    else next()
  }
)

Vue.config.productionTip = false;

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App },
});
