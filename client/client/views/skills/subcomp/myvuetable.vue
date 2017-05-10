// myvuetable.vue

<template>
  <vuetable ref="vuetable"
    api-url="http://localhost:8080/WebSkillsGW7/webresources/entis.skillpeople/findtotal"
    :fields="fields"
		pagination-path=""
		data-path=""
  ></vuetable>
</template>

<script>
	import Vuetable from 'vuetable-2/src/components/Vuetable'

	export default {
		components: {
			Vuetable
		},
		data () {
			return {
				fields: [{
						name: 'people.name',
						title: 'Title'
					},
					{
						name: 'people.email',
						title: 'Levels'
					},
					{
						name: 'level',
						title: 'Since'
					}
				]
			}
		}
	}
</script>

<style>


</style>
