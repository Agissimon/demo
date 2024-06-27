document.addEventListener('DOMContentLoaded', () => {
    const reportForm = document.getElementById('reportForm');
    const reportTableBody = document.getElementById('reportTableBody');

    reportForm.addEventListener('submit', async (event) => {
        event.preventDefault();
        const formData = new FormData(reportForm);
        const params = new URLSearchParams();

        for (const pair of formData.entries()) {
            if (pair[1]) {
                params.append(pair[0], pair[1]);
            }
        }

        try {
            const response = await fetch(`/api/v1/equipment/report?${params.toString()}`);
            const data = await response.json();

            // Очистка предыдущих результатов отчета
            reportTableBody.innerHTML = '';

            // Вставка новых результатов отчета
            data.forEach(record => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${record.serialNumber}</td>
                    <td>${record.currentStatus}</td>
                    <td>${record.location}</td>
                    <td>${new Date(record.dateStart).toLocaleDateString()}</td>
                    <td>${new Date(record.dateEnd).toLocaleDateString()}</td>
                `;
                reportTableBody.appendChild(row);
            });
        } catch (error) {
            console.error('Ошибка генерации отчета:', error);
        }
    });
});
