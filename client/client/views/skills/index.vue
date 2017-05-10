<template>
   <div class="container-fluid" style="background-color: lightgrey">
		<div class="row">
			<div class="col-md-12">
				<form role="form" class="form-inline">
					<div class="form-group input-format" >
						<div class="row">
							<label>Title</label>
						</div>
						<div class="row">
							<input type="text" placeholder="Title" class="form-control" />
						</div>
					</div>
					<div class="form-group input-format">
						<div class="row">
							<label>Level</label>
						</div>
						<div class="row">
							<v-select :options="['Junior','Senior','Master']" multiple placeholder="Level"></v-select>
						</div>
					</div>
					<!--div class="form-group input-format">
						<div class="row">
							<label>Since</label>
						</div>
						<div class="row">
							<input type="date" min="2017-01-01" placeholder="Since" class="form-control" />
						</div>
					</div-->
					<div class="form-group input-format">
						<div class="row">
							<label>Created By</label>
						</div>
						<div class="row">
							<input type="text" placeholder="Created By" class="form-control" />
						</div>
					</div>
					<div class="form-group input-format">
						<div class="row">
							<label>Last Update</label>
						</div>
						<div class="row">
							<input type="date" min="2017-01-01" placeholder="Last Update" class="form-control" />
						</div>
					</div>
					<a type="button" class="btn btn-primary"  value="Save">Add new skill</a>
				</form>
					<my-vuetable></my-vuetable>
			</div>
		</div>
	</div> 
</template>

<script>
	import MyVuetable from './subcomp/myvuetable'

	export default {
		data () {
			return {}
		},
		components: {
			MyVuetable
		},
		mounted () {
			console.log(this);
			console.log(MyVuetable);
			
		}
	}

</script>

<style>
	.input-format {
		padding-right: 30px;
	}

</style>
