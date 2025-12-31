import { useLocation, useSearchParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import { getCorsi } from '../services/corsi';
import CourseList from '../components/CourseList';

const CategoriaDettaglioPage = () => {
  const [searchParams] = useSearchParams();
  const location = useLocation();
  const categoryId = searchParams.get('categoryId');
  const categoryName = location.state?.name || 'Categoria';

  const [corsi, setCorsi] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const corsiByCategoria = await getCorsi(categoryId);
        setCorsi(corsiByCategoria);
      } catch (error) {
        console.error("Errore nel recupero dei corsi:", error);
      }
    };

    if (categoryId) {
      fetchData();
    }
  }, [categoryId]);

  return (
    <div>
      <h2>Corsi per la categoria: {categoryName}</h2>
      {corsi.length === 0 ? (
        <p>Nessun corso trovato per questa categoria.</p>
      ) : (
        <CourseList courses={corsi} />
      )}
    </div>
  );
};

export default CategoriaDettaglioPage;