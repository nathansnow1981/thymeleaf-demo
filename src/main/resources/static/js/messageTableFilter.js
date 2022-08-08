/**
 * Filters a given dataset (in this case messages) based on the given value
 * @param {*} value The filter input string
 * @param {*} data The messages
 * @returns
 */
const filterMessages = (value, data) => {
	var filteredData = [];
	for (var i = 0; i < data.length; i++) {
		value = value.toLowerCase();
		var id = data[i].id;
		var email = data[i].email.toLowerCase();
		var message = data[i].message.toLowerCase();

		if (id == value || email.includes(value) || message.includes(value)) {
			filteredData.push(data[i]);
		}
	}
	return filteredData;
};

/**
 * Rebuilds the messages table using only the items in the given dataset
 * @param {*} data The dataset of messages
 */
const rebuildTable = (data) => {
	var table = document.getElementById("messageData");
	table.innerHTML = ``;
	for (let i = 0; i < data.length; i++) {
		var row = `<tr>
                       <th scope="row">${data[i].id}</th>
                       <td>${data[i].email}</td>
                       <td>${data[i].message}</td>
                       <td><a class="btn btn-info" href='/edit/${data[i].id}'>Edit</a></td>
                       <td><a class="btn btn-danger" href='/delete/${data[i].id}' >Delete</a></td>
                   </tr>`;
		table.innerHTML += row;
	}
};
