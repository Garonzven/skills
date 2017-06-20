import Vue from 'vue';
import Router from 'vue-router';
import Login from '@/components/Login';
import Home from '@/components/Home';
import mySkills from '@/components/mySkills';
import listUser from '@/components/listUser';
import createUser from '@/components/createUser';

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/login',
      name: 'Login',
      component: Login,
      meta: {
        forVisitors: true
      }
    },
    {
      path: '/',
      name: 'Home',
      component: Home,
      meta: {
        forAuth: true
      },
      children: [
        {
          path: 'mySkills',
          component: mySkills
        },
        {
          path: 'listUser',
          component: listUser
        },
        {
          path: 'createUser',
          component: createUser
        }
      ]
    },
  ],
});
