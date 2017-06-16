import Vue from 'vue';
import Router from 'vue-router';
import Login from '@/components/Login';
import Home from '@/components/Home';
import mySkills from '@/components/mySkills';

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
        // UserHome will be rendered inside User's <router-view>
        // when /user/:id is matched
        //{ path: '', component: UserHome },

        // UserProfile will be rendered inside User's <router-view>
        // when /user/:id/profile is matched
        //{ path: 'profile', component: UserProfile },

        // UserPosts will be rendered inside User's <router-view>
        // when /user/:id/posts is matched
        { path: 'mySkills', component: mySkills }
      ]
    },
  ],
});
