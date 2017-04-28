import Vue from 'vue';
import Router from 'vue-router';
import createUser from '@/components/createUser';
import Register from '@/components/Register';
Vue.use(Router);
export default new Router({
	routes: [
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