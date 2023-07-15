const patientInfoTable = document.getElementById("patient-demographics");
insertPatientDemographics();

async function insertPatientDemographics() {
    try {
    const response = await fetch('http://localhost:8080/demo/all');
    const patientInfo = await response.json();
    patientInfo.forEach(p => {
        patientInfoTable.innerHTML += `
         <tr>
            <td>${p.name}</td>
            <td>${p.email}</td>
         </tr>
        `
    })
    console.log(patientInfo);
    } catch(error) {
        console.error(error.message || error);
    }
}