// myvuetable.vue

<template>
  <vuetable ref="vuetable"
    api-url="http://localhost:8080/WebSkillsGW7/webresources/entis.skillpeople/listfindBy/"
    :fields="fields" pagination-path="" data-path="" :http-options="options"
  ></vuetable>
</template>

<script>
  import Vuetable from 'vuetable-2/src/components/Vuetable'
  import moment from 'moment'

  export default {
    components: {
      Vuetable
    },
    data() {
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
            titleClass: 'centered',
            title: 'last updated',
            callback: 'formatDate|YYYY MMM D'
          }
        ],
        options: {
          headers: {
            "Content-Type": "application/json"
          }
        }
      }
    },
    methods: {
      //...
      formatDate(value, fmt = 'D MMM YYYY') {
        return (value == null) ?
          '' :
          moment(value, 'YYYY-MM-DD').format(fmt)
      }
    }
  }

</script>

<style>
  .centered {
    text-align: center;
  }

</style>
