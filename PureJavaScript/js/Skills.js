Vue.use(Vuetable);
new Vue({
  el: '#app',
  components:{
   'vuetable-pagination': Vuetable.VuetablePagination
  },
  data: {
  fields: ['name', 'email','birthdate','nickname','gender','__slot:actions']
  },
  computed:{
  /*httpOptions(){
    return {headers: {'Authorization': "my-token"}} //table props -> :http-options="httpOptions"
  },*/
 },
 methods: {
    onPaginationData (paginationData) {
      this.$refs.pagination.setPaginationData(paginationData)
    },
    onChangePage (page) {
      this.$refs.vuetable.changePage(page)
    },
    editRow(rowData){
      alert("You clicked edit on"+ JSON.stringify(rowData))
    },
    deleteRow(rowData){
      alert("You clicked delete on"+ JSON.stringify(rowData))
    }
  }
})