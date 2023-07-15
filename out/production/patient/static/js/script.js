insertPatientDemographics();

async function insertPatientDemographics() {
    try {
    const response = await fetch('http://host.docker.internal:8080/demo/all');
    const patientInfo = await response.json();
    console.log(patientInfo);
    } catch(error) {
        console.error(error.message || error);
    }
}