import Vue from 'vue';
import Resource from 'vue-resource';
import Vuex from 'vuex';
import VeeValidate from 'vee-validate';
import vSelect from 'vue-select';
import App from './App';
import router from './router';
import Auth from './packages/auth/Auth.js';
import apiUrl from './config/config.js';


Vue.use(apiUrl);

//console.log(Vue.api);

Vue.use(Vuex);
Vue.use(Resource);
Vue.use(Auth);
Vue.use(VeeValidate);
Vue.component('v-select', vSelect);


router.beforeEach(
  (to, from, next) => {
    if (to.matched.some(record => record.meta.forVisitors)) {
      if (Vue.auth.isAuthenticated()) {
        next({
          path: '/mySkills'
        })
      } else next()
    } else if (to.matched.some(record => record.meta.forAuth)) {
      if (!Vue.auth.isAuthenticated()) {
        next({
          path: '/login'
        })
      } else next()
    } else if (to.matched.some(record => record.meta.forAuth)) {
      if (!Vue.auth.isAuthenticated()) {
        next({
          path: '/changePassword'
        })
      } else next()
    } else next()
  }
)

Vue.config.productionTip = false;

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: {
    App
  },
});
