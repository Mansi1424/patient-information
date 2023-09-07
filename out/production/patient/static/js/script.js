function fetchItems() {
    fetch('/patient/get')
        .then(response => response.json())
        .then(data => {
            const tableBody = document.querySelector('#patient-demographics tbody');
            tableBody.innerHTML = ''; // Clear existing data if any
            console.log(data);
            // Loop through the data and create table rows
            data.forEach(item => {
                const row = document.createElement('tr');
                row.setAttribute('data-row-id', item.id);

                //Name column
                const nameCell = document.createElement("td");
                nameCell.innerText = item.name;
                nameCell.contentEditable = true;
                row.appendChild(nameCell);

                //Family Name column
                const familyNameCell = document.createElement("td");
                familyNameCell.innerText = item.familyName;
                familyNameCell.contentEditable = true;
                row.appendChild(familyNameCell);

                //DOB column
                const dobCell = document.createElement("td");
                dobCell.innerText = item.dateOfBirth;
                dobCell.contentEditable = true;
                row.appendChild(dobCell);

                //Gender column
                const genderCell = document.createElement("td");
                genderCell.innerText = item.sex;
                genderCell.contentEditable = true;
                row.appendChild(genderCell);

                //Home address column
                const homeAddressCell = document.createElement("td");
                homeAddressCell.innerText = item.homeAddress;
                homeAddressCell.contentEditable = true;
                row.appendChild(homeAddressCell);

                //Phone Num column
                const phoneNumberCell = document.createElement("td");
                phoneNumberCell.innerText = item.phoneNumber;
                phoneNumberCell.contentEditable = true;
                row.appendChild(phoneNumberCell);

                tableBody.appendChild(row);
            });
        })
        .catch(error => console.error('Error fetching data:', error));
}



// Function to add new patient to the table when the button is clicked
function updateTable() {
    fetchItems();
}



// Function to handle form submission and add new row to the table
function addNewPatient(event) {
    event.preventDefault();
    const form = event.target;
    const patientName = form.name.value;
    const familyName = form.familyName.value;
    const dob = form.dob.value;
    const gender = form.gender.value;
    const address = form.address.value;
    const phone = form.phone.value;


    // Create a new row for the new item and add it to the table
    const tableBody = document.querySelector('#patient-demographics tbody');
    const newRow = document.createElement('tr');
    newRow.innerHTML = `
        <td contenteditable="true">${patientName}</td>
        <td contenteditable="true">${familyName}</td>
        <td contenteditable="true">${dob}</td>
        <td contenteditable="true">${gender}</td>
        <td contenteditable="true">${address}</td>
        <td contenteditable="true">${phone}</td>
    `;
    tableBody.appendChild(newRow);

    console.log(tableBody);
    console.log(newRow);

    // Clear the form fields
    form.reset();

    const formData = {
        name: patientName,
        familyName: familyName,
        dateOfBirth: dob,
        sex: gender,
        homeAddress: address,
        phoneNumber: phone
    };

    // Send the form data to the POST API using fetch
    fetch('/patient/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData),
    })
    .then(data => {
        // Handle the API response, if needed
        console.log('API response:', data);
        location.assign("./");
    })
    .catch(error => console.error('Error submitting data:', error));
}


function getRowId(row) {
    return row.getAttribute('data-row-id');
 }

async function saveChanges() {
    const tableBody = document.querySelector("#table-body");
    const rows = tableBody.querySelectorAll('tr');
    const updatedData = [];
    console.log(tableBody);
    console.log("rows " + rows);


     rows.forEach(row => {
       const cells = row.querySelectorAll('td');
       const dataObject = {
         id: getRowId(row),
         name: cells[0].textContent,
         familyName: cells[1].textContent,
         dateOfBirth: cells[2].textContent,
         sex: cells[3].textContent,
         homeAddress: cells[4].textContent,
         phoneNumber: cells[5].textContent
       };
       updatedData.push(dataObject);
     });

    console.log(updatedData)
    sendPutRequest(updatedData);
}

function sendPutRequest(data) {

  fetch('patient/update', {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(data),
  })
  .then(response => {
    if (response.ok) {
      console.log('Data updated successfully.');
    } else {
      console.error('Failed to update data.');
    }
  })
  .then(response => response.json())
      .then(data => {
          // Handle the API response, if needed
          console.log('API response:', data);
          location.assign("./");
      })
  .catch(error => {
    console.error('Error occurred while updating data:', error);
  });
}

// Add click event to call to saveChanges() to update patient table
document.getElementById('updatePatientButton').addEventListener('click', () => {
    saveChanges();
});

// Add a click event listener to the "Update Table" button
document.getElementById('addNewPatient').addEventListener('click', updateTable);

// Add a submit event listener to the form
document.getElementById('addPatientForm').addEventListener('submit', addNewPatient);

// Call the fetchItems function when the page loads
window.onload = fetchItems;

