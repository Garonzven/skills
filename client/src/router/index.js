import Vue from 'vue';
import Router from 'vue-router';
import createUser from '@/components/createUser';
import Register from '@/components/Register';
import Login from '@/components/authentication/Login';
import Recovery from '@/components/authentication/Recovery'

Vue.use(Router);

export default new Router({
	routes: [
		{
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      path: '/recovery',
      name: 'Recovery',
      component: Recovery
    },
		 {
			path: '/Register'
			, name: 'register'
			, component: Register
    }

		, {
			path: '/createUser'
			, name: 'createUser'
			, component: createUser
    }
  ]
});
