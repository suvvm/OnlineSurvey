<template>
	<view>
		<van-steps :active="active">
			<van-step>注册账号</van-step>
			<van-step>选择角色</van-step>
			<van-step>选择喜好</van-step>
			<van-step>注册完成</van-step>
		</van-steps>
		<van-button type="primary" size="large" @click="show">继续</van-button>
		<van-index-bar v-model="res">
			<van-index-anchor index="A" />
				<van-cell v-for="(item,key) in tags.list" :key="item.id" v-if="item.pingin == 'A'" clickable :title="item.name" :value="item.description" @click="clickTag(item.id, key)">
					<van-checkbox  :ref="'checkbox'+key" v-model="tags.like[key]" slot="right-icon" shape="square"></van-checkbox>
				</van-cell>
			<van-index-anchor index="B" />
				<van-cell v-for="(item,key) in tags.list" :key="item.id" v-if="item.pingin == 'B'" clickable :title="item.name" :value="item.description" @click="clickTag(item.id, key)">
					<van-checkbox  :ref="'checkbox'+key" v-model="tags.like[key]" slot="right-icon" shape="square"></van-checkbox>
				</van-cell>
			<van-index-anchor index="C" />
				<van-cell v-for="(item,key) in tags.list" :key="item.id" v-if="item.pingin == 'C'" clickable :title="item.name" :value="item.description" @click="clickTag(item.id, key)">
					<van-checkbox  :ref="'checkbox'+key" v-model="tags.like[key]" slot="right-icon" shape="square"></van-checkbox>
				</van-cell>
			<van-index-anchor index="D" />
				<van-cell v-for="(item,key) in tags.list" :key="item.id" v-if="item.pingin == 'D'" clickable :title="item.name" :value="item.description" @click="clickTag(item.id, key)">
					<van-checkbox  :ref="'checkbox'+key" v-model="tags.like[key]" slot="right-icon" shape="square"></van-checkbox>
				</van-cell>
			<van-index-anchor index="E" />
				<van-cell v-for="(item,key) in tags.list" :key="item.id" v-if="item.pingin == 'E'" clickable :title="item.name" :value="item.description" @click="clickTag(item.id, key)">
					<van-checkbox  :ref="'checkbox'+key" v-model="tags.like[key]" slot="right-icon" shape="square"></van-checkbox>
				</van-cell>
			<van-index-anchor index="F" />
				<van-cell v-for="(item,key) in tags.list" :key="item.id" v-if="item.pingin == 'F'" clickable :title="item.name" :value="item.description" @click="clickTag(item.id, key)">
					<van-checkbox  :ref="'checkbox'+key" v-model="tags.like[key]" slot="right-icon" shape="square"></van-checkbox>
				</van-cell>
			<van-index-anchor index="G" />
				<van-cell v-for="(item,key) in tags.list" :key="item.id" v-if="item.pingin == 'G'" clickable :title="item.name" :value="item.description" @click="clickTag(item.id, key)">
					<van-checkbox  :ref="'checkbox'+key" v-model="tags.like[key]" slot="right-icon" shape="square"></van-checkbox>
				</van-cell>
			<van-index-anchor index="H" />
				<van-cell v-for="(item,key) in tags.list" :key="item.id" v-if="item.pingin == 'H'" clickable :title="item.name" :value="item.description" @click="clickTag(item.id, key)">
					<van-checkbox  :ref="'checkbox'+key" v-model="tags.like[key]" slot="right-icon" shape="square"></van-checkbox>
				</van-cell>
			<van-index-anchor index="I" />
				<van-cell v-for="(item,key) in tags.list" :key="item.id" v-if="item.pingin == 'I'" clickable :title="item.name" :value="item.description" @click="clickTag(item.id, key)">
					<van-checkbox  :ref="'checkbox'+key" v-model="tags.like[key]" slot="right-icon" shape="square"></van-checkbox>
				</van-cell>
			<van-index-anchor index="J" />
				<van-cell v-for="(item,key) in tags.list" :key="item.id" v-if="item.pingin == 'J'" clickable :title="item.name" :value="item.description" @click="clickTag(item.id, key)">
					<van-checkbox  :ref="'checkbox'+key" v-model="tags.like[key]" slot="right-icon" shape="square"></van-checkbox>
				</van-cell>
			<van-index-anchor index="K" />
				<van-cell v-for="(item,key) in tags.list" :key="item.id" v-if="item.pingin == 'K'" clickable :title="item.name" :value="item.description" @click="clickTag(item.id, key)">
					<van-checkbox  :ref="'checkbox'+key" v-model="tags.like[key]" slot="right-icon" shape="square"></van-checkbox>
				</van-cell>
			<van-index-anchor index="L" />
				<van-cell v-for="(item,key) in tags.list" :key="item.id" v-if="item.pingin == 'L'" clickable :title="item.name" :value="item.description" @click="clickTag(item.id, key)">
					<van-checkbox  :ref="'checkbox'+key" v-model="tags.like[key]" slot="right-icon" shape="square"></van-checkbox>
				</van-cell>
			<van-index-anchor index="M" />
				<van-cell v-for="(item,key) in tags.list" :key="item.id" v-if="item.pingin == 'M'" clickable :title="item.name" :value="item.description" @click="clickTag(item.id, key)">
					<van-checkbox  :ref="'checkbox'+key" v-model="tags.like[key]" slot="right-icon" shape="square"></van-checkbox>
				</van-cell>
			<van-index-anchor index="N" />
				<van-cell v-for="(item,key) in tags.list" :key="item.id" v-if="item.pingin == 'N'" clickable :title="item.name" :value="item.description" @click="clickTag(item.id, key)">
					<van-checkbox  :ref="'checkbox'+key" v-model="tags.like[key]" slot="right-icon" shape="square"></van-checkbox>
				</van-cell>
			<van-index-anchor index="O" />
				<van-cell v-for="(item,key) in tags.list" :key="item.id" v-if="item.pingin == 'O'" clickable :title="item.name" :value="item.description" @click="clickTag(item.id, key)">
					<van-checkbox  :ref="'checkbox'+key" v-model="tags.like[key]" slot="right-icon" shape="square"></van-checkbox>
				</van-cell>
			<van-index-anchor index="P" />
				<van-cell v-for="(item,key) in tags.list" :key="item.id" v-if="item.pingin == 'P'" clickable :title="item.name" :value="item.description" @click="clickTag(item.id, key)">
					<van-checkbox  :ref="'checkbox'+key" v-model="tags.like[key]" slot="right-icon" shape="square"></van-checkbox>
				</van-cell>
			<van-index-anchor index="Q" />
				<van-cell v-for="(item,key) in tags.list" :key="item.id" v-if="item.pingin == 'Q'" clickable :title="item.name" :value="item.description" @click="clickTag(item.id, key)">
					<van-checkbox  :ref="'checkbox'+key" v-model="tags.like[key]" slot="right-icon" shape="square"></van-checkbox>
				</van-cell>
			<van-index-anchor index="R" />
				<van-cell v-for="(item,key) in tags.list" :key="item.id" v-if="item.pingin == 'R'" clickable :title="item.name" :value="item.description" @click="clickTag(item.id, key)">
					<van-checkbox  :ref="'checkbox'+key" v-model="tags.like[key]" slot="right-icon" shape="square"></van-checkbox>
				</van-cell>
			<van-index-anchor index="S" />
				<van-cell v-for="(item,key) in tags.list" :key="item.id" v-if="item.pingin == 'S'" clickable :title="item.name" :value="item.description" @click="clickTag(item.id, key)">
					<van-checkbox  :ref="'checkbox'+key" v-model="tags.like[key]" slot="right-icon" shape="square"></van-checkbox>
				</van-cell>
			<van-index-anchor index="T" />
				<van-cell v-for="(item,key) in tags.list" :key="item.id" v-if="item.pingin == 'T'" clickable :title="item.name" :value="item.description" @click="clickTag(item.id, key)">
					<van-checkbox  :ref="'checkbox'+key" v-model="tags.like[key]" slot="right-icon" shape="square"></van-checkbox>
				</van-cell>
			<van-index-anchor index="U" />
				<van-cell v-for="(item,key) in tags.list" :key="item.id" v-if="item.pingin == 'U'" clickable :title="item.name" :value="item.description" @click="clickTag(item.id, key)">
					<van-checkbox  :ref="'checkbox'+key" v-model="tags.like[key]" slot="right-icon" shape="square"></van-checkbox>
				</van-cell>
			<van-index-anchor index="V" />
				<van-cell v-for="(item,key) in tags.list" :key="item.id" v-if="item.pingin == 'V'" clickable :title="item.name" :value="item.description" @click="clickTag(item.id, key)">
					<van-checkbox  :ref="'checkbox'+key" v-model="tags.like[key]" slot="right-icon" shape="square"></van-checkbox>
				</van-cell>
			<van-index-anchor index="W" />
				<van-cell v-for="(item,key) in tags.list" :key="item.id" v-if="item.pingin == 'W'" clickable :title="item.name" :value="item.description" @click="clickTag(item.id, key)">
					<van-checkbox  :ref="'checkbox'+key" v-model="tags.like[key]" slot="right-icon" shape="square"></van-checkbox>
				</van-cell>
			<van-index-anchor index="X" />
				<van-cell v-for="(item,key) in tags.list" :key="item.id" v-if="item.pingin == 'X'" clickable :title="item.name" :value="item.description" @click="clickTag(item.id, key)">
					<van-checkbox  :ref="'checkbox'+key" v-model="tags.like[key]" slot="right-icon" shape="square"></van-checkbox>
				</van-cell>
			<van-index-anchor index="Y" />
				<van-cell v-for="(item,key) in tags.list" :key="item.id" v-if="item.pingin == 'Y'" clickable :title="item.name" :value="item.description" @click="clickTag(item.id, key)">
					<van-checkbox  :ref="'checkbox'+key" v-model="tags.like[key]" slot="right-icon" shape="square"></van-checkbox>
				</van-cell>
			<van-index-anchor index="Z" />
				<van-cell v-for="(item,key) in tags.list" :key="item.id" v-if="item.pingin == 'Z'" clickable :title="item.name" :value="item.description" @click="clickTag(item.id, key)">
					<van-checkbox  :ref="'checkbox'+key" v-model="tags.like[key]" slot="right-icon" shape="square"></van-checkbox>
				</van-cell>	
		</van-index-bar>
		
	</view>
	
</template>

<script>
	export default {
		data() {
			return {
				active: 2,
				loading: true,
				userInfo: {},
				tags: {"like":[false],"list":[]},
				res:[]				
			}
		},
		onLoad() {
			if(this.$cookies.get("userInfo") != null){
				this.userInfo = this.$cookies.get("userInfo");
			}
			let pinyin = require('js-pinyin');
			pinyin.setOptions({checkPolyphone: false, charCase: 0});
			var rp = require('request-promise');
			rp('http://localhost:8080/getTags').then(res => {
			        // 获取所有Tag成功
					this.tags.list = JSON.parse(res);
					for (var i = 0; i < this.tags.list.length; i++) {
						var hp =  pinyin.getCamelChars(this.tags.list[i].name);
						this.tags.list[i].pingin = hp[0];
						this.tags.like[i] = false;
						// console.log(this.tags[i]);
					}
					 console.log(this.tags);
					
			    }).catch(err => {
			        // 获取失败
					console.log(err)
			    });
		},
		methods: {
			// 每个标签点击时通过Checkbox实例上的 toggle 方法触发选择状态切换
			clickTag(id, key){	
				this.$refs[`checkbox`+`${key}`][0].toggle();
				console.log(this.$refs);
				console.log(this.tags[key])
			}
		}
	}
</script>

<style>
	
</style>
