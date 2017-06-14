import Vue from 'vue';
import Router from 'vue-router';
import Login from '@/components/Login';
import Home from '@/components/Home';

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/login',
      name: 'Login',
      component: Login,
      /*meta: {
        forVisitors: true
      }*/
    },
    {
      path: '/',
      name: 'Home',
      component: Home,
      /*meta: {
        forAuth: true
      }*/
    },
  ],
});
