<template>
	<view>
		
	<van-field
		v-model="testText"
		label="用户名"
	/>
	<van-button class="submit" type="primary"  size="large" @click="handleClick">确定</van-button>
	</view>
</template>

<script>
	export default {
		data () {
			return {
				testText: ""
			}
		},
		methods: {
			handleClick() {
				var rp = require('request-promise');
				var options = {
				    method: 'POST',
				    uri: 'http://localhost:8080/testDemom/TestServlet',
				    form: {
				        testText: this.testText,
				    }
				};
				rp(options).then(res => {	
					console.log(res)
				}).catch(err => {
					console.log(err)
				});
			}
		}
	}
</script>

<style>

</style>
