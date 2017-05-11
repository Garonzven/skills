// myvuetable.vue

<template>
  <vuetable ref="vuetable"
    api-url="http://localhost:8080/WebSkillsGW7/webresources/entis.skillpeople/listfindBy/"
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
						name: 'dato2',
						title: 'Title'
					},
					{
						name: 'dato1',
						title: 'Created By'
					},
					{
						name: 'datetime',
						title: 'Last Updated'
					}
				]
			}
		}
	}
</script>

<style>


</style>
