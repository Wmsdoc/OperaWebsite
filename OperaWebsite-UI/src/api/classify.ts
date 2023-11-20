import { useRequest } from 'vue-request'

export const getAllOperaType = () => {
	const { data, loading, error } = useRequest(() =>
		http.get('/opera/type/getAllOperaType'),
	)
	return { data, loading, error }
}
