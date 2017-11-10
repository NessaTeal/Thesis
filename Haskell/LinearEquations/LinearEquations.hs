
main :: IO ()
main = print $ map (/determinant) (multiplyMatrixAndVector adjugate vector)
  where matrix = [[1,1,1],[0,2,5],[2,5,-1]]
        vector = [6,-4,27]
        determinant = getDeterminant matrix
        adjugate = getAdjugate matrix

getMinor :: (Fractional a) => Int -> Int -> [[a]] -> a
getMinor i j matrix = (-1) ^^ (i + j) * value
  where matrixWithoutRow = map snd $ filter (\x -> i /= fst x) $ zip [0..] matrix
        reducedMatrix = map (map snd . filter (\y -> j /= fst y) . zip [0..]) matrixWithoutRow
        value = getDeterminant reducedMatrix

getDeterminant :: (Fractional a) => [[a]] -> a
getDeterminant matrix = if len == 2
                        then a * d - b * c
                        else sum $ zipWith (*) (head matrix) $ map (\y -> getMinor 0 y matrix) [0..(len - 1)]
                          where len = length matrix
                                a = head $ head matrix
                                b = last $ head matrix
                                c = head $ last matrix
                                d = last $ last matrix

getAdjugate :: (Fractional a) => [[a]] -> [[a]]
getAdjugate matrix = map (map (\tuple -> uncurry getMinor tuple matrix)) matrixOfIndices
  where len = length matrix
        matrixOfIndices = map (\y -> map (\x ->(x,y)) [0..(len - 1)]) [0..(len - 1)]

multiplyMatrixAndVector :: (Fractional a) => [[a]] -> [a] -> [a]
multiplyMatrixAndVector matrix vector = map (sum . zipWith (*) vector) matrix
