import qualified Data.Map.Lazy as Map
import qualified Data.Set as Set

main :: IO ()
main = print $ findShortestDistance createGraph (Node 0)

type Id = Int
type Cost = Int
type Distance = Int

newtype Node = Node Id deriving (Show, Eq, Ord)
type Origin = Node
type Destination = Node

createGraph::Map.Map Origin (Map.Map Destination Cost)
createGraph = Map.fromList [(Node 0, Map.fromList [(Node 1, 2), (Node 2, 3), (Node 3, 8)]),
                (Node 1, Map.fromList [(Node 0, 3), (Node 2, 3), (Node 3, 9)]),
                (Node 2, Map.fromList [(Node 0, 5), (Node 1, 3), (Node 3, 2)]),
                (Node 3, Map.fromList [(Node 0, 2), (Node 1, 1), (Node 2, 3)])]

findShortestDistance::Map.Map Origin (Map.Map Destination Cost) -> Origin -> Map.Map Node Distance
findShortestDistance edges origin = calculateOneNode (Map.singleton origin 0) Set.empty origin
  where calculateOneNode globalShortestDistances calculated currentNode
          | null globalNeedToVisit = globalShortestDistances
          | otherwise = calculateOneNode (traverseAllPaths globalNeedToVisit globalShortestDistances) (Set.insert currentNode calculated) (head globalNeedToVisit)
          where globalNeedToVisit = Set.toList $ Set.difference (Map.keysSet (edges Map.! currentNode)) calculated
                traverseAllPaths needToVisit shortestDistances
                  | null needToVisit = shortestDistances
                  | otherwise = traverseAllPaths (tail needToVisit) newShortestDistances
                    where neighbor = head needToVisit
                          newDistance = (edges Map.! currentNode) Map.! neighbor + shortestDistances Map.! currentNode
                          newShortestDistances = if not (neighbor `Map.member` shortestDistances) || newDistance < shortestDistances Map.! neighbor
                                                 then Map.insert neighbor newDistance shortestDistances
                                                 else shortestDistances
